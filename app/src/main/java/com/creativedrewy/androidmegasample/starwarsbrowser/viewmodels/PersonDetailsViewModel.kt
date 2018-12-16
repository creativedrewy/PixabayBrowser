package com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels

import android.arch.lifecycle.ViewModel
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.OmdbLoadRepository
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.SwapiLoadRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PersonDetailsViewModel @Inject constructor(
        private val swapiLoadRepository: SwapiLoadRepository,
        private val omdbLoadRepository: OmdbLoadRepository
): ViewModel() {

    fun getMoviePosterUrls(movieIds: List<Int>): Observable<List<String>> {
        return Observable.fromArray(movieIds)
                .flatMapIterable { it }
                .flatMap { filmId ->
                    swapiLoadRepository.getFilmDetails(filmId)
                }
                .flatMap { swapiMovie ->
                    omdbLoadRepository.loadFilmDetails(
                            createSearchableTitle(swapiMovie.title),
                            createSearchableYear(swapiMovie.releaseDate))
                }
                .map { it.Poster }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun createSearchableTitle(sourceTitle: String): String {
        return sourceTitle.replace(" ", "+")
    }

    fun createSearchableYear(sourceYear: String): String {
        return sourceYear.split("-")[0]
    }

}