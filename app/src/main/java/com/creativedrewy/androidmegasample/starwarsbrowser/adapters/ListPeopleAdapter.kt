package com.creativedrewy.androidmegasample.starwarsbrowser.adapters

import android.content.Intent
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.starwarsbrowser.activities.PersonDetailsActivity
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PersonPreviewVO
import kotlinx.android.synthetic.main.item_person_list.view.*

class ListPeopleAdapter: ListAdapter<PersonPreviewVO, ListPeopleAdapter.ViewHolder>(PeopleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(person: PersonPreviewVO) {
            view.full_name_textview.text = person.fullName

            view.setOnClickListener {
                view.context.startActivity(Intent(view.context, PersonDetailsActivity::class.java)
                        .apply {
                            putExtra(PersonDetailsActivity.EXTRA_SELECTED_PERSON, person)
                        })
            }
        }
    }
}