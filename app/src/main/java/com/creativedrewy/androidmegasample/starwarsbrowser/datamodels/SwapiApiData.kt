package com.creativedrewy.androidmegasample.starwarsbrowser.datamodels

import com.google.gson.annotations.SerializedName

/**
 * Top level result object from people search
 */
data class PeopleResults(val results: List<Character>)

data class Character(
        val name: String,
        val gender: String,
        val films: List<String>
)

/**
 * Top level result object from film url
 */
data class SwapiMovieResult(
        val title: String,
        @SerializedName("release_date")
        val releaseDate: String
)