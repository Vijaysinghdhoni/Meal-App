package com.vijaydhoni.meal.model.api

import com.vijaydhoni.meal.model.dataModels.meal.MealList
import com.vijaydhoni.meal.model.dataModels.mealByArea.MealAreaList
import com.vijaydhoni.meal.model.dataModels.mealByArea.allMealsByArea.AllMealsByAreaList
import com.vijaydhoni.meal.model.dataModels.mealCategory.MealCategoryesList
import com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory.AllMealsByCategoryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MealDBService {

    @GET("search.php")
    suspend fun getMealByName(
        @Query("s") mealName: String
    ): Response<MealList>

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") mealid: String
    ): Response<MealList>

    @GET("categories.php")
    suspend fun getAllCategories(): Response<MealCategoryesList>

    @GET("list.php?a=list")
    suspend fun getAllAreas(): Response<MealAreaList>

    @GET("filter.php")
    suspend fun getAllMealsByCategories(
        @Query("c") category: String
    ): Response<AllMealsByCategoryList>

    @GET("filter.php")
    suspend fun getAllMealsByArea(
        @Query("a") category: String
    ): Response<AllMealsByAreaList>

}