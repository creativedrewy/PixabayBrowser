package com.creativedrewy.androidmegasample.starwarsbrowser.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.creativedrewy.androidmegasample.R
import com.creativedrewy.androidmegasample.starwarsbrowser.adapters.MoviePostersAdapter
import com.creativedrewy.androidmegasample.starwarsbrowser.datamodels.PersonPreviewVO
import com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels.AppViewModelFactory
import com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels.PersonDetailsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_person_details.*
import javax.inject.Inject

class PersonDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SELECTED_PERSON = "extraSelectedPerson"
    }

    @Inject
    lateinit var viewModeFactory: AppViewModelFactory

    private val adapter: MoviePostersAdapter by lazy { MoviePostersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        (intent.extras?.getSerializable(EXTRA_SELECTED_PERSON) as PersonPreviewVO?)?.let { personVO ->
            name_textview.text = personVO.fullName
            gender_textview.text = personVO.gender

            movie_posters_recyclerview.layoutManager = GridLayoutManager(this, 2)
            movie_posters_recyclerview.adapter = adapter

            val viewModel = ViewModelProviders.of(this, viewModeFactory).get(PersonDetailsViewModel::class.java)

            posters_loading_progressbar.visibility = View.VISIBLE
            viewModel.getMoviePosterUrls(personVO.filmIds)
                    .subscribe({
                        posters_loading_progressbar.visibility = View.GONE
                        adapter.submitList(it)
                    }, {
                        posters_loading_progressbar.visibility = View.GONE
                        Log.e("Andrew", "There was an error getting your person", it)
                    })
        }
    }
}
