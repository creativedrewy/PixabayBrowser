package com.creativedrewy.sample.pixabaybrowser.endpoints

import com.creativedrewy.sample.pixabaybrowser.datamodels.Hits
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayEndpoints {

    @GET("videos/")
    suspend fun loadVideos(@Query("key") key: String, @Query("q") term: String): Hits
}