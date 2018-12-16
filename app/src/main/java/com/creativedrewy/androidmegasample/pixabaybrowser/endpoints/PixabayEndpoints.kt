package com.creativedrewy.androidmegasample.pixabaybrowser.endpoints

import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.Hits
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayEndpoints {

    @GET("videos/")
    fun loadVideos(@Query("key") key: String, @Query("q") term: String): Observable<Hits>
}