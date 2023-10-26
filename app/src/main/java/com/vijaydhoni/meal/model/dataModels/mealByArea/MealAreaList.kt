package com.vijaydhoni.meal.model.dataModels.mealByArea


import com.google.gson.annotations.SerializedName

data class MealAreaList(
    @SerializedName("meals")
    val meals: List<MealArea>
)