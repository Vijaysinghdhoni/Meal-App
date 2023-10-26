package com.vijaydhoni.meal.model.dataModels.mealCategory


import com.google.gson.annotations.SerializedName

data class MealCategoryesList(
    @SerializedName("categories")
    val categories: List<MealCategory>
)