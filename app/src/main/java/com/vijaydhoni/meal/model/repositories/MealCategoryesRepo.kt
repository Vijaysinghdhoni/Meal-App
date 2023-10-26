package com.vijaydhoni.meal.model.repositories

import com.vijaydhoni.meal.model.api.MealDBService
import com.vijaydhoni.meal.model.dataModels.mealCategory.MealCategoryesList
import retrofit2.Response
import javax.inject.Inject

class MealCategoryesRepo @Inject constructor(private val mealDBService: MealDBService) {

    suspend fun getAllMealCategories(): Response<MealCategoryesList> {
        return mealDBService.getAllCategories()
    }

}