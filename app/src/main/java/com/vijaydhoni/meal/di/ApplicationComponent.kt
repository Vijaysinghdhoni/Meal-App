package com.vijaydhoni.meal.di

import com.vijaydhoni.meal.view.FilterFoodByCategoryActivity
import com.vijaydhoni.meal.view.FoodDetailActivity
import com.vijaydhoni.meal.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(filterFoodByCategoryActivity: FilterFoodByCategoryActivity)

    fun inject(foodDetailActivity: FoodDetailActivity)
}