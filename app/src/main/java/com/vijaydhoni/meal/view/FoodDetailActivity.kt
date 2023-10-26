package com.vijaydhoni.meal.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.vijaydhoni.meal.R
import com.vijaydhoni.meal.databinding.ActivityFoodDetailBinding
import com.vijaydhoni.meal.di.MealApplicationclass
import com.vijaydhoni.meal.model.dataModels.meal.MealList
import com.vijaydhoni.meal.viewModel.foodDetailActivityViewModel.FoodDetailViewModel
import com.vijaydhoni.meal.viewModel.foodDetailActivityViewModel.FoodDetailViewModelFactory
import javax.inject.Inject

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodDetailBinding
    private var mealName: String? = null
    private var mealId: String? = null
    private lateinit var youtubeDishLink: String
    private lateinit var dialog: AlertDialog
    private var sourceDishLink: String = ""

    @Inject
    lateinit var detailViewModelFactory: FoodDetailViewModelFactory
    private lateinit var detailViewModel: FoodDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_detail)

        (application as MealApplicationclass).applicationComponent.inject(this)

        detailViewModel =
            ViewModelProvider(this, detailViewModelFactory)[FoodDetailViewModel::class.java]

        loadingAlertDialogBox()

        mealName = intent.getStringExtra(FilterFoodByCategoryActivity.MEAL_NAME)
        mealId = intent.getStringExtra(FilterFoodByCategoryActivity.MEAL_ID)
        setToolbarFunctionality()

        detailViewModel.getMeal(mealId!!)
        detailViewModel.mealliveData.observe(this) {
            setUIdata(it)
        }

        detailViewModel.errorMessageLiveData.observe(this) {
            apiORiNterneterrorMessageHandle(it)
        }

        binding.youtubeCardView.setOnClickListener {
            openYoutube()
        }

        binding.sourceCardView.setOnClickListener {
            openSource()
        }

        binding.shareCardView.setOnClickListener {
            shareMeal()
        }

    }

    private fun apiORiNterneterrorMessageHandle(it: String?) {
        Snackbar.make(this, binding.root, it!!, Snackbar.LENGTH_LONG)
            .show()
        dialog.dismiss()
    }

    private fun setUIdata(mealList: MealList) {
        val meal = mealList.meals[0]
        binding.tollbarTitleCategory.text = "${meal.strCategory} || ${meal.mealArea} "
        binding.mealInstructions.text = meal.strInstructions

        youtubeDishLink = meal.strYoutube

        if (meal.strSource != null) {
            sourceDishLink = meal.strSource.toString()
        }


        Glide.with(binding.mealIdImage).load(meal.strMealThumb)
            .placeholder(R.drawable.new_food_place_hoder).diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.mealIdImage)

        val ingredientsList = listOf(
            meal.strIngredient1,
            meal.strIngredient2,
            meal.strIngredient3,
            meal.strIngredient4,
            meal.strIngredient5,
            meal.strIngredient6,
            meal.strIngredient7,
            meal.strIngredient8,
            meal.strIngredient9,
            meal.strIngredient10,
            meal.strIngredient11,
            meal.strIngredient12,
            meal.strIngredient13,
            meal.strIngredient14,
            meal.strIngredient15,
            meal.strIngredient16,
            meal.strIngredient17,
            meal.strIngredient18,
            meal.strIngredient19,
            meal.strIngredient20
        )





        for (element in ingredientsList) {
            if (element != null && element != "" && element != " ") {
                binding.tvIngredients.append("$element\n\n")
            }

        }

        val measureList = listOf(
            meal.strMeasure1,
            meal.strMeasure2,
            meal.strMeasure3,
            meal.strMeasure4,
            meal.strMeasure5,
            meal.strMeasure6,
            meal.strMeasure7,
            meal.strMeasure8,
            meal.strMeasure9,
            meal.strMeasure10,
            meal.strMeasure11,
            meal.strMeasure12,
            meal.strMeasure13,
            meal.strMeasure14,
            meal.strMeasure15,
            meal.strMeasure16,
            meal.strMeasure17,
            meal.strMeasure18,
            meal.strMeasure19,
            meal.strMeasure20
        )




        for (element in measureList) {
            if (element != null && element != "" && element != " ") {
                binding.tvMeasure.append("$element\n\n")
            }

        }

        dialog.dismiss()

    }

    private fun setToolbarFunctionality() {
        binding.tollbarTitleDishName.text = mealName
        setSupportActionBar(binding.myToolbar)
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


    private fun openYoutube() {
        try {
            if (youtubeDishLink == "") {

                Snackbar.make(
                    this,
                    binding.root,
                    "Sorry!! Dish not available",
                    Snackbar.LENGTH_LONG
                )
                    .show()

            } else {

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(youtubeDishLink)
                intent.setPackage("com.google.android.youtube")
                startActivity(intent)
            }

        } catch (e: Exception) {
            Snackbar.make(this, binding.root, "Youtube not avilable", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun openSource() {
        try {
            if (sourceDishLink == "") {

                Snackbar.make(
                    this,
                    binding.root,
                    "Sorry!! Dish Source not available",
                    Snackbar.LENGTH_LONG
                ).show()

            } else {

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(sourceDishLink)
                startActivity(intent)
            }

        } catch (e: Exception) {
            Snackbar.make(this, binding.root, "invalidURL", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun shareMeal() {

        try {
            if (sourceDishLink == "") {

                Snackbar.make(
                    this,
                    binding.root,
                    "Sorry!! Dish can't be Shared",
                    Snackbar.LENGTH_LONG
                ).show()

            } else {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, sourceDishLink)
                startActivity(Intent.createChooser(shareIntent, "Share with"))
            }

        } catch (e: Exception) {
            Snackbar.make(this, binding.root, "invalidShare", Snackbar.LENGTH_LONG).show()
        }

    }

    private fun loadingAlertDialogBox() {

        val alertDialogbuilder = AlertDialog.Builder(this)
            .setView(R.layout.alertdialoglayout)
        dialog = alertDialogbuilder.create()
        dialog.show()

    }

}