package com.creativedrewy.androidmegasample.starwarsbrowser.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.OmdbLoadRepository
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.SwapiLoadRepository
import javax.inject.Inject

class AppViewModelFactory @Inject constructor(
        private val swapiLoadRepository: SwapiLoadRepository,
        private val omdbLoadRepository: OmdbLoadRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPeopleViewModel::class.java)) {
            return ListPeopleViewModel(swapiLoadRepository) as T
        }

        if (modelClass.isAssignableFrom(PersonDetailsViewModel::class.java)) {
            return PersonDetailsViewModel(swapiLoadRepository, omdbLoadRepository) as T
        }

        throw Exception("You are trying to instantiate a ViewModel that doesn't exist.")
    }
}