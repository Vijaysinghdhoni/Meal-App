package com.vijaydhoni.meal.model.dataModels.mealByArea.allMealsByArea


import com.google.gson.annotations.SerializedName

data class AllMealsByArea(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)