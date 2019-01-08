package com.creativedrewy.androidmegasample.pixabaybrowser.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO
import com.creativedrewy.androidmegasample.pixabaybrowser.repositories.VideoLoadRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BrowseVideosViewModel(
    private val repository: VideoLoadRepository
): ViewModel() {

    fun loadVideoPreviews(searchTerm: String): Observable<List<VideoPreviewVO>> {
        return repository.loadVideos(searchTerm)
                .map { result ->
                    result.hits.map { VideoPreviewVO(it.picture_id, it.videos.medium.url) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    class Factory @Inject constructor(
        private val pixabayRepository: VideoLoadRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BrowseVideosViewModel::class.java)) {
                return BrowseVideosViewModel(pixabayRepository) as T
            }

            throw Exception("You are trying to instantiate a ViewModel that doesn't exist.")
        }
    }
}