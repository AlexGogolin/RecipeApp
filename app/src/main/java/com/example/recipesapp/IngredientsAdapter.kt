package com.example.recipesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.ItemIngredientBinding
import com.example.recipesapp.model.Ingredient

class IngredientsAdapter(private val dataSet: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemIngredientBinding.inflate(inflater, viewGroup, false)

        return IngredientsAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val ingredient: Ingredient = dataSet[position]
        holder.binding.tvIngredientName.text = ingredient.description
        holder.binding.tvIngredientQuantity.text = ingredient.quantity
        holder.binding.tvUnitOfMeasure.text = ingredient.unitOfMeasure
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}