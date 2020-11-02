package com.creativedrewy.androidmegasample.pixabaybrowser.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.pixabaybrowser.adapters.ListVideosAdapter
import com.creativedrewy.androidmegasample.pixabaybrowser.viewmodels.BrowseVideosViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class PixabaySearchActivity : AppCompatActivity() {

    private val defaultSearchTerm = "dogs"
    private val adapter: ListVideosAdapter by lazy {
        ListVideosAdapter()
    }

    @Inject
    lateinit var browseViewModelFactory: BrowseVideosViewModel.Factory

    private lateinit var browseViewModel: BrowseVideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pixabay)
        setSupportActionBar(browse_toolbar)

        images_list_recyclerview.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.browse_column_count))
        images_list_recyclerview.adapter = adapter

        browseViewModel = ViewModelProviders.of(this, browseViewModelFactory).get(BrowseVideosViewModel::class.java)
        browseViewModel.videoPreviews.observe(this, Observer {
            adapter.submitList(it)
        })

        browseViewModel.loadVideoPreviews(defaultSearchTerm)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_browse, menu)

        (menu?.findItem(R.id.action_search)?.actionView as SearchView?)
                ?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { browseViewModel.loadVideoPreviews(it) }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
