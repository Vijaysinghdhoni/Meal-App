package com.vijaydhoni.meal.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vijaydhoni.meal.R
import com.vijaydhoni.meal.databinding.ActivityMainBinding
import com.vijaydhoni.meal.di.MealApplicationclass
import com.vijaydhoni.meal.model.dataModels.mealCategory.MealCategory
import com.vijaydhoni.meal.view.adapters.MainActivityAdapter
import com.vijaydhoni.meal.viewModel.mainActivityViewModel.MainActivityViewModel
import com.vijaydhoni.meal.viewModel.mainActivityViewModel.MainActivityViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityAdapter.ItemClickListner {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityAdapter: MainActivityAdapter
    private lateinit var dialog: AlertDialog

    @Inject
    lateinit var factory: MainActivityViewModelFactory
    private lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        const val categoryName = "category_name"
        const val categoryDiscription = "category_discription"
        const val categoryImage = "category_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        (application as MealApplicationclass).applicationComponent.inject(this)

        mainActivityViewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]

        mainActivityViewModel.getAllMealsCategories()

        mainActivityAdapter = MainActivityAdapter(this)
        setAdapter()

        // binding.progressBar.visibility = View.VISIBLE
        loadingAlertDialogBox()
        mainActivityViewModel.getAllMealsCategories()

        mainActivityViewModel.categoryesList.observe(this) {
            val mealCategoryes = it.categories
            setRecylerView(mealCategoryes)
        }

        mainActivityViewModel.errorMessageLiveData.observe(this) {
            apiORiNterneterrorMessageHandle(it)
        }

    }

    private fun apiORiNterneterrorMessageHandle(s: String) {
        Snackbar.make(this, binding.root, s, Snackbar.LENGTH_LONG)
            .show()
        dialog.dismiss()

    }

    private fun setRecylerView(mealCategoryes: List<MealCategory>) {
        mainActivityAdapter.setList(mealCategoryes)
        mainActivityAdapter.notifyDataSetChanged()
        // binding.progressBar.visibility = View.GONE
        dialog.dismiss()
    }

    private fun setAdapter() {
        binding.categoriesRecylerView.adapter = mainActivityAdapter
        binding.categoriesRecylerView.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
    }

    override fun onClick(mealCategory: MealCategory) {
        val intent = Intent(this, FilterFoodByCategoryActivity::class.java)
        intent.putExtra(categoryName, mealCategory.strCategory)
        intent.putExtra(categoryDiscription, mealCategory.strCategoryDescription)
        intent.putExtra(categoryImage, mealCategory.strCategoryThumb)
        startActivity(intent)
    }


    private fun loadingAlertDialogBox() {

        val alertDialogbuilder = AlertDialog.Builder(this)
            .setView(R.layout.alertdialoglayout)
        dialog = alertDialogbuilder.create()
        dialog.show()

    }

}