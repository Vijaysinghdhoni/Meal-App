package com.vijaydhoni.meal.model.dataModels.meal


import com.google.gson.annotations.SerializedName
import com.vijaydhoni.meal.model.dataModels.meal.Meal

data class MealList(
    @SerializedName("meals")
    val meals: List<Meal>
)