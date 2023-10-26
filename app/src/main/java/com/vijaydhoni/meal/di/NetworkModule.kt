package com.vijaydhoni.meal.di

import com.vijaydhoni.meal.BuildConfig
import com.vijaydhoni.meal.model.api.MealDBService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    private val base_url = "https://www.themealdb.com/api/json/v1/1/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieDbApi(retrofit: Retrofit): MealDBService {
        return retrofit.create(MealDBService::class.java)
    }
}