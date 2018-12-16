package com.creativedrewy.androidmegasample.starwarsbrowser.repositories

import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PeopleResults
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.SwapiMovieResult
import com.creativedrewy.androidmegasample.starwarsbrowser.endpoints.SwapiEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class SwapiLoadRepository @Inject constructor(
        private val endpoint: SwapiEndpoints
) {
    fun searchPeople(term: String): Observable<PeopleResults> {
        return endpoint.searchPeople(term)
    }

    fun getFilmDetails(id: Int): Observable<SwapiMovieResult> {
        return endpoint.getFilmDetails(id)
    }
}