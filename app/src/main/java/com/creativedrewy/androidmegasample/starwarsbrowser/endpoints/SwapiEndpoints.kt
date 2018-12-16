package com.creativedrewy.androidmegasample.starwarsbrowser.endpoints

import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PeopleResults
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.SwapiMovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiEndpoints {

    @GET("people/")
    fun searchPeople(@Query("search") term: String): Observable<PeopleResults>

    @GET("films/{filmId}/")
    fun getFilmDetails(@Path("filmId") id: Int): Observable<SwapiMovieResult>
}