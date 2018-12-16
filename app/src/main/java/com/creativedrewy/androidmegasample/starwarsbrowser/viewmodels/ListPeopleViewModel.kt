package com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels

import android.arch.lifecycle.ViewModel
import android.net.Uri
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PersonPreviewVO
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.SwapiLoadRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListPeopleViewModel @Inject constructor(
        private val repository: SwapiLoadRepository
): ViewModel() {

    fun searchStarWarsPeople(searchTerm: String): Observable<List<PersonPreviewVO>> {
        return repository.searchPeople(searchTerm)
                .map {
                    it.results.map { character ->
                        PersonPreviewVO(character.name,
                                character.gender.capitalize(),
                                character.films.map { fullUrl ->
                                    parseFilmId(fullUrl)
                                })
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Given a Swapi film url - e.g. "https://swapi.co/api/films/1/" -
     * just grab the file id and convert it to an int
     */
    private fun parseFilmId(filmUrl: String): Int {
        val uri = Uri.parse(filmUrl)
        return if (uri.pathSegments.size > 0) {
            val filmId = uri.pathSegments[uri.pathSegments.size - 1]

            try { filmId.toInt() } catch (e: Exception) { 0 }
        }
        else { 0 }
    }
}