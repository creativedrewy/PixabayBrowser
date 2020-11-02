package com.creativedrewy.sample.pixabaybrowser.repositories

import com.creativedrewy.sample.BuildConfig
import com.creativedrewy.sample.pixabaybrowser.datamodels.Hits
import com.creativedrewy.sample.pixabaybrowser.endpoints.PixabayEndpoints
import javax.inject.Inject

class VideoLoadRepository @Inject constructor(
    private val endpoint: PixabayEndpoints
) {

    suspend fun loadVideos(term: String): Hits {
        return endpoint.loadVideos(BuildConfig.PIXABAY_API_KEY, term)
    }
}