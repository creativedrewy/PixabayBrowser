package com.creativedrewy.androidmegasample.pixabaybrowser.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.pixabaybrowser.adapters.ListVideosAdapter
import com.creativedrewy.androidmegasample.pixabaybrowser.viewmodels.BrowseVideosViewModel
import kotlinx.android.synthetic.main.activity_browse.*

class BrowseActivity : AppCompatActivity() {

    private val defaultSearchTerm = "dogs"
    private val adapter: ListVideosAdapter by lazy {
        ListVideosAdapter()
    }

    private lateinit var browseViewModel: BrowseVideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pixabay)
        setSupportActionBar(browse_toolbar)

        images_list_recyclerview.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.browse_column_count))
        images_list_recyclerview.adapter = adapter

        browseViewModel = ViewModelProviders.of(this).get(BrowseVideosViewModel::class.java)
        performSearch(defaultSearchTerm)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_browse, menu)

        (menu?.findItem(R.id.action_search)?.actionView as SearchView?)
                ?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { performSearch(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun performSearch(term: String) {
        browseViewModel.loadVideoPreviews(term)
                .subscribe({
                    adapter.submitList(it)
                }, {
                    Log.e("Andrew", "There was an error loading unfortunately", it)
                })
    }
}
