package com.creativedrewy.androidmegasample.starwarsbrowser.datamodels

import java.io.Serializable

/**
 * View specific data object to display in people list
 */
data class PersonPreviewVO(
        val fullName: String,
        val gender: String,
        val filmIds: List<Int>
): Serializable //Yes, parcelable is better but I'm being lazy