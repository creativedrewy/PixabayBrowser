package com.creativedrewy.androidmegasample.pixabaybrowser.adapters

import android.support.v7.util.DiffUtil
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO

class VideosDiffCallback: DiffUtil.ItemCallback<VideoPreviewVO>() {

    override fun areItemsTheSame(oldItem: VideoPreviewVO, newItem: VideoPreviewVO): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoPreviewVO, newItem: VideoPreviewVO): Boolean {
        return oldItem.videoUrl == newItem.videoUrl &&
                oldItem.thumbnailUrl == newItem.thumbnailUrl
    }

}