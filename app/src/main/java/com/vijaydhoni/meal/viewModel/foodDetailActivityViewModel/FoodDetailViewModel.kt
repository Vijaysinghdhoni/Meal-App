package com.vijaydhoni.meal.viewModel.foodDetailActivityViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vijaydhoni.meal.model.dataModels.meal.MealList
import com.vijaydhoni.meal.model.repositories.DetailMealRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class FoodDetailViewModel(private val detailMealRepo: DetailMealRepo) : ViewModel() {

    private val mealData: MutableLiveData<MealList> = MutableLiveData<MealList>()
    private val errorMessageMutableLiveData = MutableLiveData<String>()

    val mealliveData: LiveData<MealList>
        get() = mealData
    val errorMessageLiveData: LiveData<String>
        get() = errorMessageMutableLiveData

    fun getMeal(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mealList = detailMealRepo.getMealById(id)
                if (mealList.isSuccessful) {
                    mealData.postValue(mealList.body())
                } else {
                    errorMessageMutableLiveData.postValue("API Error: ${mealList.code()} - ${mealList.message()}")
                }
            } catch (ex: IOException) {
                errorMessageMutableLiveData.postValue("Network Error: ${ex.message}")
            }

        }

    }

}