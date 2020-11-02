package com.creativedrewy.sample.pixabaybrowser.injection

import com.creativedrewy.sample.BuildConfig
import com.creativedrewy.sample.pixabaybrowser.endpoints.PixabayEndpoints
import com.creativedrewy.sample.pixabaybrowser.repositories.VideoLoadRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
class PixabayModule {

    @Provides
    @Named("pixabay")
    fun provideRetrofitPixabay(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.PIXABAY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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