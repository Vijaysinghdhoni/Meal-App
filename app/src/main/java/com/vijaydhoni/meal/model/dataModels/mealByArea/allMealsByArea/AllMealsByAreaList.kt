package com.vijaydhoni.meal.model.dataModels.mealByArea.allMealsByArea


import com.google.gson.annotations.SerializedName

data class AllMealsByAreaList(
    @SerializedName("meals")
    val meals: List<AllMealsByArea>
)