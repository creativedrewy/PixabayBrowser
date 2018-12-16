package com.creativedrewy.androidmegasample.starwarsbrowser.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.starwarsbrowser.adapters.ListPeopleAdapter
import com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels.AppViewModelFactory
import com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels.ListPeopleViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class PeopleListActivity : AppCompatActivity() {

    private val defaultSearchTerm = "Luke"
    private val adapter: ListPeopleAdapter by lazy { ListPeopleAdapter() }

    private lateinit var listPeopleViewModel: ListPeopleViewModel

    @Inject
    lateinit var viewModeFactory: AppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        setSupportActionBar(browse_toolbar)

        images_list_recyclerview.layoutManager = LinearLayoutManager(this)
        images_list_recyclerview.adapter = adapter

        listPeopleViewModel = ViewModelProviders.of(this, viewModeFactory).get(ListPeopleViewModel::class.java)

        updateTitleWithSearch(defaultSearchTerm)
        performSearch(defaultSearchTerm)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_browse, menu)

        val searchMenuItem = menu?.findItem(R.id.action_search)
        (searchMenuItem?.actionView as SearchView?)?.let { searchView ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        updateTitleWithSearch(it)
                        performSearch(it)
                    }

                    searchMenuItem?.collapseActionView()
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean = false
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun updateTitleWithSearch(term: String) {
        supportActionBar?.title = "${getString(R.string.searching_title_text)} $term"
    }

    private fun performSearch(term: String) {
        listPeopleViewModel.searchStarWarsPeople(term)
                .subscribe({
                    adapter.submitList(it)
                }, {
                    Log.e("Andrew", "There was an error loading unfortunately", it)
                })
    }
}
