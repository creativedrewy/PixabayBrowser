package com.creativedrewy.sample.pixabaybrowser.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.creativedrewy.sample.pixabaybrowser.datamodels.VideoPreview
import com.creativedrewy.sample.pixabaybrowser.repositories.VideoLoadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BrowseViewState(
    val videoPreviews: List<VideoPreview> = listOf()
)

class BrowseVideosViewModel(
    private val repository: VideoLoadRepository
): ViewModel() {

    val viewState: MutableLiveData<BrowseViewState> = MutableLiveData()

    fun loadVideoPreviews(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val loadedVideos = repository.loadVideos(searchTerm)

            val result = loadedVideos.hits.map { VideoPreview(it.picture_id, it.videos.medium.url) }
            viewState.postValue(BrowseViewState(result))
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