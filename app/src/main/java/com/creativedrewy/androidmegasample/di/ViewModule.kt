package com.creativedrewy.androidmegasample.di

import com.creativedrewy.androidmegasample.pixabaybrowser.activities.PixabaySearchActivity
import com.creativedrewy.androidmegasample.starwarsbrowser.activities.PersonDetailsActivity
import com.creativedrewy.androidmegasample.starwarsbrowser.activities.StarWarsPeopleListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun bindPeopleListActivity(): StarWarsPeopleListActivity

    @ContributesAndroidInjector
    abstract fun bindPersonDetailsActivity(): PersonDetailsActivity

    @ContributesAndroidInjector
    abstract fun bindPixabaySearchActivity(): PixabaySearchActivity
}