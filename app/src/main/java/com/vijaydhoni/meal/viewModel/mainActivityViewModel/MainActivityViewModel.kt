package com.vijaydhoni.meal.viewModel.mainActivityViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vijaydhoni.meal.model.dataModels.mealCategory.MealCategoryesList
import com.vijaydhoni.meal.model.repositories.MealCategoryesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivityViewModel(private val mealCategoryesRepo: MealCategoryesRepo) : ViewModel() {

    private val categoryes = MutableLiveData<MealCategoryesList>()
    private val errorMessageMutableLiveData = MutableLiveData<String>()

    val categoryesList: LiveData<MealCategoryesList>
        get() = categoryes

    val errorMessageLiveData: LiveData<String>
        get() = errorMessageMutableLiveData

     fun getAllMealsCategories(){

       viewModelScope.launch(Dispatchers.IO) {

           try{
               val response = mealCategoryesRepo.getAllMealCategories()
               if(response.isSuccessful){
                   categoryes.postValue(response.body())
               }else{
                   errorMessageMutableLiveData.postValue("API Error: ${response.code()} - ${response.message()}")
               }
           }catch(ex: IOException){
               errorMessageMutableLiveData.postValue("Network Error: ${ex.message}")
           }

       }

    }


}