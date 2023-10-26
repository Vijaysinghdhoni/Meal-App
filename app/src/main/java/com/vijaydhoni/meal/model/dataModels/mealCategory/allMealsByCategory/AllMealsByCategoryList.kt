package com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory


import com.google.gson.annotations.SerializedName

data class AllMealsByCategoryList(
    @SerializedName("meals")
    val meals: List<AllMealsByCategory>
)