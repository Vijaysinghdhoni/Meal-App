package com.vijaydhoni.meal.model.repositories

import com.vijaydhoni.meal.model.api.MealDBService
import com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory.AllMealsByCategoryList
import retrofit2.Response
import javax.inject.Inject

class AllMealsByCategoryesRepo @Inject constructor(private val mealDBService: MealDBService) {

    suspend fun getAllMealsByGivenCategory(category: String): Response<AllMealsByCategoryList> {

        return mealDBService.getAllMealsByCategories(category)
    }


}
