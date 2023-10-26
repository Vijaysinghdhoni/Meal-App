package com.vijaydhoni.meal.viewModel.filterFoodByCategoryViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vijaydhoni.meal.model.repositories.AllMealsByCategoryesRepo
import javax.inject.Inject

class FilterFoodByCateViewModelFactory @Inject constructor(private val allMealsByCategoryesRepo: AllMealsByCategoryesRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilterFoodByCategoryViewModel(allMealsByCategoryesRepo) as T
    }
}