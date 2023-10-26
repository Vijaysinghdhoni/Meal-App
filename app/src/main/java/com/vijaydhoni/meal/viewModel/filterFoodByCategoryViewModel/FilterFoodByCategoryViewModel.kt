package com.vijaydhoni.meal.viewModel.filterFoodByCategoryViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory.AllMealsByCategory
import com.vijaydhoni.meal.model.repositories.AllMealsByCategoryesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class FilterFoodByCategoryViewModel(private val allMealsByCategoryesRepo: AllMealsByCategoryesRepo) :
    ViewModel() {

    private val allMealsByCategoryMutableLiveData = MutableLiveData<List<AllMealsByCategory>>()
    private val errorMessageMutableLiveData = MutableLiveData<String>()

    val allMealByCategoryLiveData: LiveData<List<AllMealsByCategory>>
        get() = allMealsByCategoryMutableLiveData

    val errorMessageLiveData: LiveData<String>
        get() = errorMessageMutableLiveData


    fun filterAllMealCategoryes(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = allMealsByCategoryesRepo.getAllMealsByGivenCategory(category)
                if (response.isSuccessful) {
                    allMealsByCategoryMutableLiveData.postValue(response.body()?.meals)
                } else {
                    errorMessageMutableLiveData.postValue("API Error: ${response.code()} - ${response.message()}")
                }
            } catch (ex: IOException) {
                errorMessageMutableLiveData.postValue("Network Error: ${ex.message}")
            }

        }
    }
}