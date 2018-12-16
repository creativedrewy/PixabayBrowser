package com.creativedrewy.androidmegasample.pixabaybrowser.datamodels

data class Hits(val hits: List<VideoDetails>)

data class VideoDetails(
        val picture_id: String,
        val videos: VideoSet
)

data class VideoSet(val medium: VideoProps)

data class VideoProps(
        val url: String
)