package com.vijaydhoni.meal.model.repositories

import com.vijaydhoni.meal.model.api.MealDBService
import com.vijaydhoni.meal.model.dataModels.meal.MealList
import retrofit2.Response
import javax.inject.Inject

class DetailMealRepo @Inject constructor(private val mealDBService: MealDBService) {

    suspend fun getMealDetailByName(mealName: String): Response<MealList> {
        return mealDBService.getMealByName(mealName)
    }

    suspend fun getMealById(mealId: String): Response<MealList> {
        return mealDBService.getMealById(mealId)
    }

}
