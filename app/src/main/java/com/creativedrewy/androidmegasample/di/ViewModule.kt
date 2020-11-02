package com.creativedrewy.androidmegasample.di

import com.creativedrewy.androidmegasample.pixabaybrowser.activities.PixabaySearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun bindPixabaySearchActivity(): PixabaySearchActivity
}