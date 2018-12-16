package com.creativedrewy.androidmegasample.di

import com.creativedrewy.androidmegasample.starwarsbrowser.activities.PeopleListActivity
import com.creativedrewy.androidmegasample.starwarsbrowser.activities.PersonDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun bindPeopleListActivity(): PeopleListActivity

    @ContributesAndroidInjector
    abstract fun bindPersonDetailsActivity(): PersonDetailsActivity
}