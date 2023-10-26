package com.vijaydhoni.meal.viewModel.foodDetailActivityViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vijaydhoni.meal.model.repositories.DetailMealRepo
import javax.inject.Inject

class FoodDetailViewModelFactory @Inject constructor(private val detailMealRepo: DetailMealRepo) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodDetailViewModel(detailMealRepo) as T
    }
}