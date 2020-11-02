package com.creativedrewy.sample.pixabaybrowser.adapters

import androidx.recyclerview.widget.DiffUtil
import com.creativedrewy.sample.pixabaybrowser.datamodels.VideoPreview

class VideosDiffCallback: DiffUtil.ItemCallback<VideoPreview>() {

    override fun areItemsTheSame(oldItem: VideoPreview, newItem: VideoPreview): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoPreview, newItem: VideoPreview): Boolean {
        return oldItem.videoUrl == newItem.videoUrl &&
                oldItem.thumbnailUrl == newItem.thumbnailUrl
    }

}