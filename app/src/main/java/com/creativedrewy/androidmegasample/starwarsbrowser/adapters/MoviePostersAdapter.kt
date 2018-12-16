package com.creativedrewy.androidmegasample.starwarsbrowser.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativedrewy.androidmegasample.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_move_poster.view.*

class MoviePostersAdapter: ListAdapter<String, MoviePostersAdapter.ViewHolder>(
        object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        })
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePostersAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_move_poster, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(posterUrl: String) {
            Picasso.get()
                    .load(posterUrl)
                    .into(view.poster_imageview)
        }
    }
}