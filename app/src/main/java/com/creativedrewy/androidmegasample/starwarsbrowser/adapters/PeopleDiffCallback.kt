package com.creativedrewy.androidmegasample.starwarsbrowser.adapters

import android.support.v7.util.DiffUtil
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PersonPreviewVO

class PeopleDiffCallback: DiffUtil.ItemCallback<PersonPreviewVO>() {

    override fun areItemsTheSame(oldItem: PersonPreviewVO, newItem: PersonPreviewVO): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PersonPreviewVO, newItem: PersonPreviewVO): Boolean {
        return oldItem.filmIds == newItem.filmIds &&
                oldItem.gender == newItem.gender &&
                oldItem.fullName == newItem.fullName
    }

}