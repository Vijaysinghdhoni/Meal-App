package com.vijaydhoni.meal.viewModel.mainActivityViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vijaydhoni.meal.model.repositories.MealCategoryesRepo
import javax.inject.Inject

class MainActivityViewModelFactory @Inject constructor(private val mealCategoryesRepo: MealCategoryesRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(mealCategoryesRepo) as T
    }


}