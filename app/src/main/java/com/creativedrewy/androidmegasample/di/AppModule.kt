package com.creativedrewy.androidmegasample.di

import android.content.Context
import com.creativedrewy.androidmegasample.App
import com.creativedrewy.androidmegasample.BuildConfig
import com.creativedrewy.androidmegasample.starwarsbrowser.endpoints.OmdbEndpoints
import com.creativedrewy.androidmegasample.starwarsbrowser.endpoints.SwapiEndpoints
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.OmdbLoadRepository
import com.creativedrewy.androidmegasample.starwarsbrowser.repositories.SwapiLoadRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    @Named("swapi")
    fun provideRetrofitSwapi(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SWAPI_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    fun provideSwapiEndpoints(@Named("swapi") retrofit: Retrofit): SwapiEndpoints {
        return retrofit.create(SwapiEndpoints::class.java)
    }

    @Provides
    fun provideSwapiLoadRepository(endpoint: SwapiEndpoints): SwapiLoadRepository {
        return SwapiLoadRepository(endpoint)
    }

    @Provides
    @Named("omdb")
    fun provideRetrofitOmdb(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.OMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    fun provideOmdbEndpoints(@Named("omdb") retrofit: Retrofit): OmdbEndpoints {
        return retrofit.create(OmdbEndpoints::class.java)
    }

    @Provides
    fun provideOmdbLoadRepository(endpoint: OmdbEndpoints): OmdbLoadRepository {
        return OmdbLoadRepository(endpoint)
    }
}