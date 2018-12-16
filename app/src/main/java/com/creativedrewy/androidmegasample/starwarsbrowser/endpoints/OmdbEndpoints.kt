package com.creativedrewy.androidmegasample.starwarsbrowser.endpoints

import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.OmdbMovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbEndpoints {

    @GET("/")
    fun getMovieDetails(
            @Query("apikey") key: String,
            @Query("t") title: String,
            @Query("y") year: String): Observable<OmdbMovieResult>
}