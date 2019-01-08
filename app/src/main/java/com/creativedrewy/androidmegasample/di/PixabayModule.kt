package com.creativedrewy.androidmegasample.di

import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.pixabaybrowser.endpoints.PixabayEndpoints
import com.creativedrewy.androidmegasample.pixabaybrowser.repositories.VideoLoadRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class PixabayModule {
    @Provides
    @Named("pixabay")
    fun provideRetrofitPixabay(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PIXABAY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providePixabayEndpoints(@Named("pixabay") retrofit: Retrofit): PixabayEndpoints {
        return retrofit.create(PixabayEndpoints::class.java)
    }

    @Provides
    fun provideVideoLoadRepository(endpoints: PixabayEndpoints): VideoLoadRepository {
        return VideoLoadRepository(endpoints)
    }
}