package com.creativedrewy.androidmegasample.pixabaybrowser.repositories

import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.Hits
import com.creativedrewy.androidmegasample.pixabaybrowser.endpoints.PixabayEndpoints
import javax.inject.Inject

class VideoLoadRepository @Inject constructor(
    private val endpoint: PixabayEndpoints
) {

    suspend fun loadVideos(term: String): Hits {
        return endpoint.loadVideos(BuildConfig.PIXABAY_API_KEY, term)
    }
}