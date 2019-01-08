package com.creativedrewy.androidmegasample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.creativedrewy.androidmegasample.pixabaybrowser.activities.PixabaySearchActivity
import com.creativedrewy.androidmegasample.starwarsbrowser.activities.StarWarsPeopleListActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        pixabay_button.setOnClickListener {
            startActivity(Intent(this, PixabaySearchActivity::class.java))
        }

        starwars_button.setOnClickListener {
            startActivity(Intent(this, StarWarsPeopleListActivity::class.java))
        }
    }
}
