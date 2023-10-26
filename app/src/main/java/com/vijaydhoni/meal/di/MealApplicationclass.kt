package com.vijaydhoni.meal.di

import android.app.Application

class MealApplicationclass : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}