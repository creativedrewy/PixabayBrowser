package com.creativedrewy.androidmegasample.pixabaybrowser.viewmodels

import android.arch.lifecycle.ViewModel
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO
import com.creativedrewy.androidmegasample.pixabaybrowser.repositories.VideoLoadRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BrowseVideosViewModel: ViewModel() {

    private val repository = VideoLoadRepository()

    fun loadVideoPreviews(searchTerm: String): Observable<List<VideoPreviewVO>> {
        return repository.loadVideos(searchTerm)
                .map {
                    it.hits.map { VideoPreviewVO(it.picture_id, it.videos.medium.url) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}