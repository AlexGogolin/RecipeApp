package com.example.recipesapp.ui.recipes.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.ItemIngredientBinding
import com.example.recipesapp.model.Ingredient

class IngredientsAdapter(private val dataSet: List<Ingredient>, private var quantity: Int = 1) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemIngredientBinding.inflate(inflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val ingredient: Ingredient = dataSet[position]
        holder.binding.tvIngredientName.text = ingredient.description
        holder.binding.tvUnitOfMeasure.text = ingredient.unitOfMeasure

        val quantityText = ingredient.quantity.toDoubleOrNull()
        if (quantityText == null) {
            holder.binding.tvIngredientQuantity.text = ingredient.quantity
        } else {
            val portions = quantityText * quantity
            if (portions % 1 == 0.0) {
                holder.binding.tvIngredientQuantity.text = portions.toInt().toString()
            } else {
                holder.binding.tvIngredientQuantity.text = "%.1f".format(portions)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateIngredients(progress: Int) {
        quantity = progress
        notifyDataSetChanged()
    }
}
