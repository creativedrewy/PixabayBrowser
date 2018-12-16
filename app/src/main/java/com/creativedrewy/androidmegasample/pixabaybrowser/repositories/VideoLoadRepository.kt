package com.creativedrewy.androidmegasample.pixabaybrowser.repositories

import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.Hits
import com.creativedrewy.androidmegasample.pixabaybrowser.endpoints.PixabayEndpoints
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class VideoLoadRepository {
    private val endpoint: PixabayEndpoints

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.PIXABAY_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        endpoint = retrofit.create(PixabayEndpoints::class.java)
    }

    fun loadVideos(term: String): Observable<Hits> {
        return endpoint.loadVideos(BuildConfig.PIXABAY_API_KEY, term)
    }
}