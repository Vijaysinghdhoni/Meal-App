package com.vijaydhoni.meal.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vijaydhoni.meal.R
import com.vijaydhoni.meal.databinding.CategoryItemLayoutBinding
import com.vijaydhoni.meal.model.dataModels.mealCategory.MealCategory

class MainActivityAdapter(private var itemClickListner: ItemClickListner) :
    RecyclerView.Adapter<MyViewholder>() {
    private val categoryList = ArrayList<MealCategory>()


    fun setList(list: List<MealCategory>) {
        categoryList.clear()
        categoryList.addAll(list)
    }

    interface ItemClickListner {
        fun onClick(mealCategory: MealCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CategoryItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.category_item_layout, parent, false)
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.bind(categoryList[position], itemClickListner)
    }


}


class MyViewholder(
    private val binding: CategoryItemLayoutBinding
) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(mealCategory: MealCategory, itemClickListner: MainActivityAdapter.ItemClickListner) {
        binding.tvKategori.text = mealCategory.strCategory

        Glide.with(binding.imgKategori)
            .load(mealCategory.strCategoryThumb)
            .placeholder(R.drawable.ic_food_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgKategori)

        binding.root.setOnClickListener {
            itemClickListner.onClick(mealCategory)
        }

    }


}