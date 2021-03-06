package com.creativedrewy.sample.pixabaybrowser.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.creativedrewy.sample.R
import com.creativedrewy.sample.pixabaybrowser.adapters.ListVideosAdapter
import com.creativedrewy.sample.pixabaybrowser.viewmodels.BrowseVideosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

@AndroidEntryPoint
class PixabaySearchActivity : AppCompatActivity() {

    private val defaultSearchTerm = "dogs"
    private val adapter: ListVideosAdapter by lazy { ListVideosAdapter() }

    @Inject
    lateinit var browseViewModelFactory: BrowseVideosViewModel.Factory

    private lateinit var browseViewModel: BrowseVideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pixabay)
        setSupportActionBar(browse_toolbar)

        images_list_recyclerview.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.browse_column_count))
        images_list_recyclerview.adapter = adapter

        browseViewModel = ViewModelProvider(this, browseViewModelFactory).get(BrowseVideosViewModel::class.java)
        browseViewModel.viewState.observe(this, Observer { viewState ->
            viewState?.videoPreviews?.let {
                adapter.submitList(it)
            }
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
