package com.creativedrewy.androidmegasample.starwarsbrowser.repositories

import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.OmdbMovieResult
import com.creativedrewy.androidmegasample.starwarsbrowser.endpoints.OmdbEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class OmdbLoadRepository @Inject constructor(
        private val omdbEndpoints: OmdbEndpoints
) {
    fun loadFilmDetails(title: String, year: String): Observable<OmdbMovieResult> {
        return omdbEndpoints.getMovieDetails(BuildConfig.OMDB_API_KEY, title, year)
    }
}