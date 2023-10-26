package com.vijaydhoni.meal.model.dataModels.mealCategory


import com.google.gson.annotations.SerializedName

data class MealCategory(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)