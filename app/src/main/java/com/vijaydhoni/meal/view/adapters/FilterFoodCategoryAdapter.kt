package com.vijaydhoni.meal.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vijaydhoni.meal.R
import com.vijaydhoni.meal.databinding.CategoryAllFoodsLayoutBinding
import com.vijaydhoni.meal.model.dataModels.mealCategory.allMealsByCategory.AllMealsByCategory

class FilterFoodCategoryAdapter(private val clicklistner: (AllMealsByCategory) -> Unit) :
    RecyclerView.Adapter<FilterFoodCategoryAdapter.MyViewHolder>() {

    private val allMeals = ArrayList<AllMealsByCategory>()

    fun setList(list: List<AllMealsByCategory>) {
        allMeals.clear()
        allMeals.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CategoryAllFoodsLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.category_all_foods_layout,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allMeals.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(allMeals[position], clicklistner)
    }

    inner class MyViewHolder(private val binding: CategoryAllFoodsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            allMealsByCategory: AllMealsByCategory,
            clicklistner: (AllMealsByCategory) -> Unit
        ) {
            binding.foodName.text = allMealsByCategory.strMeal

            Glide.with(binding.foodImage)
                .load(allMealsByCategory.strMealThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.new_food_place_hoder)
                .into(binding.foodImage)

            binding.root.setOnClickListener {
                clicklistner(allMealsByCategory)
            }

        }

    }

}

