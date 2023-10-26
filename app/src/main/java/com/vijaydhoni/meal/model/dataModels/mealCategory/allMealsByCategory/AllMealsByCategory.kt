package com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory


import com.google.gson.annotations.SerializedName

data class AllMealsByCategory(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)