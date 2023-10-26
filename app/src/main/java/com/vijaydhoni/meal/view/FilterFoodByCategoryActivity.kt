package com.vijaydhoni.meal.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.vijaydhoni.meal.R
import com.vijaydhoni.meal.databinding.ActivityFilterFoodByCategoryBinding
import com.vijaydhoni.meal.di.MealApplicationclass
import com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory.AllMealsByCategory
import com.vijaydhoni.meal.view.adapters.FilterFoodCategoryAdapter
import com.vijaydhoni.meal.viewModel.filterFoodByCategoryViewModel.FilterFoodByCateViewModelFactory
import com.vijaydhoni.meal.viewModel.filterFoodByCategoryViewModel.FilterFoodByCategoryViewModel
import javax.inject.Inject

class FilterFoodByCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterFoodByCategoryBinding
    private lateinit var categoryname: String
    private lateinit var categoryDiscription: String
    private lateinit var categoryImage: String
    private lateinit var categoryAdapter: FilterFoodCategoryAdapter
    private lateinit var dialog: AlertDialog

    @Inject
    lateinit var filterFoodByCateViewModelFactory: FilterFoodByCateViewModelFactory
    private lateinit var filterFoodByCategoryViewModel: FilterFoodByCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_food_by_category)

        (application as MealApplicationclass).applicationComponent.inject(this)

        filterFoodByCategoryViewModel = ViewModelProvider(
            this,
            filterFoodByCateViewModelFactory
        )[FilterFoodByCategoryViewModel::class.java]


        loadingAlertDialogBox()

        categoryname = intent.getStringExtra(MainActivity.categoryName).toString()
        categoryDiscription = intent.getStringExtra(MainActivity.categoryDiscription).toString()
        categoryImage = intent.getStringExtra(MainActivity.categoryImage).toString()

        filterFoodByCategoryViewModel.filterAllMealCategoryes(categoryname)

        setToolbarFunctionalites()
        setCategoryDiscriptionAndImageThumb()
        setRecylerView()

        filterFoodByCategoryViewModel.allMealByCategoryLiveData.observe(this) {
            setFoodFromApiIntoRecylerView(it)
        }

        filterFoodByCategoryViewModel.errorMessageLiveData.observe(this) {
            apiORiNterneterrorMessageHandle(it)
        }

    }

    private fun apiORiNterneterrorMessageHandle(s: String) {
        Snackbar.make(this, binding.root, s, Snackbar.LENGTH_LONG)
            .show()
        dialog.dismiss()
    }

    private fun setFoodFromApiIntoRecylerView(it: List<AllMealsByCategory>?) {
        if (it != null) {
            categoryAdapter.setList(it)
            categoryAdapter.notifyDataSetChanged()
        } else {
            Snackbar.make(this, binding.root, "Please Enter valid Food name", Snackbar.LENGTH_LONG)
                .show()
        }

        dialog.dismiss()

    }

    private fun setRecylerView() {
        categoryAdapter = FilterFoodCategoryAdapter { allMealsByCategory: AllMealsByCategory ->
            onClick(allMealsByCategory)
        }
        binding.categoryFoodListRecylerView.adapter = categoryAdapter
        binding.categoryFoodListRecylerView.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
    }

    private fun setToolbarFunctionalites() {
        binding.tollbarTitle.text = "Food List $categoryname"

        setSupportActionBar(binding.toolabarId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setCategoryDiscriptionAndImageThumb() {
        binding.categoryDiscription.text = categoryDiscription

        Glide.with(binding.imgCategories)
            .load(categoryImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgCategories)

        Glide.with(binding.fullCateImage)
            .load(categoryImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.fullCateImage)


    }

    private fun onClick(allMealsByCategory: AllMealsByCategory) {

        val intent = Intent(this, FoodDetailActivity::class.java)
        intent.putExtra(MEAL_ID, allMealsByCategory.idMeal)
        intent.putExtra(MEAL_NAME, allMealsByCategory.strMeal)
        startActivity(intent)

    }


    private fun loadingAlertDialogBox() {

        val alertDialogbuilder = AlertDialog.Builder(this)
            .setView(R.layout.alertdialoglayout)
        dialog = alertDialogbuilder.create()
        dialog.show()

    }


    companion object {
        const val MEAL_ID = "MEAL_ID"
        const val MEAL_NAME = "Meal_name"

    }

}