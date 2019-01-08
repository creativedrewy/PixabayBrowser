package com.creativedrewy.androidmegasample.pixabaybrowser.repositories

import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.Hits
import com.creativedrewy.androidmegasample.pixabaybrowser.endpoints.PixabayEndpoints
import io.reactivex.Observable
import javax.inject.Inject

class VideoLoadRepository @Inject constructor(
    private val endpoint: PixabayEndpoints
) {

    fun loadVideos(term: String): Observable<Hits> {
        return endpoint.loadVideos(BuildConfig.PIXABAY_API_KEY, term)
    }
}