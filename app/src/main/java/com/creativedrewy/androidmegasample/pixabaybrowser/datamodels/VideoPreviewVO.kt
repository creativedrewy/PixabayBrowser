package com.creativedrewy.androidmegasample.pixabaybrowser.datamodels

import java.io.Serializable

/**
 * View specific data object to display video previews
 */
data class VideoPreviewVO(
        val thumbnailUrl: String,
        val videoUrl: String
): Serializable