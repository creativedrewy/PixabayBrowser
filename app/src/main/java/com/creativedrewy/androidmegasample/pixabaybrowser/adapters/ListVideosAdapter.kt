package com.creativedrewy.androidmegasample.pixabaybrowser.adapters

import android.content.Intent
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.pixabaybrowser.activities.VideoPlayerActivity
import com.creativedrewy.androidmegasample.pixabaybrowser.datamodels.VideoPreviewVO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_preview.view.*

class ListVideosAdapter: ListAdapter<VideoPreviewVO, ListVideosAdapter.ViewHolder>(VideosDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video_preview, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(video: VideoPreviewVO) {
            view.setOnClickListener {
                view.context.startActivity(Intent(view.context, VideoPlayerActivity::class.java)
                        .apply {
                            putExtra(VideoPlayerActivity.EXTRA_VIDEO_DATA, video)
                        })
            }

            Picasso.get()
                    .load("https://i.vimeocdn.com/video/${video.thumbnailUrl}_960x540.jpg")
                    .into(view.video_preview_imageview)
        }
    }
}