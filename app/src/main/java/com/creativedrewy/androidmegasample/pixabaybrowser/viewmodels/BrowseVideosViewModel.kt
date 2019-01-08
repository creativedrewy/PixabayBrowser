package com.creativedrewy.androidmegasample.pixabaybrowser.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.creativedrewy.androidmegasample.common.BaseViewModel
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO
import com.creativedrewy.androidmegasample.pixabaybrowser.repositories.VideoLoadRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BrowseVideosViewModel(
    private val repository: VideoLoadRepository
): BaseViewModel() {

    val videoPreviews: MutableLiveData<List<VideoPreviewVO>> = MutableLiveData()

    fun loadVideoPreviews(searchTerm: String) {
        performLoadOperation {
            repository.loadVideos(searchTerm)
                .map { result ->
                    result.hits.map { VideoPreviewVO(it.picture_id, it.videos.medium.url) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    videoPreviews.value = it
                }, {
                    Log.e("Andrew", "There was an error loading unfortunately", it)
                })
        }
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