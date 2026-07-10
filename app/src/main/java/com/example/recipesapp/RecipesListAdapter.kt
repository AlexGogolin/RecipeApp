package com.example.recipesapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import com.example.recipesapp.databinding.ItemRecipeBinding
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.model.Recipe


class RecipesListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(recipeId: Int)
    }
    var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemClickListener = listener
    }

    class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemRecipeBinding.inflate(inflater, viewGroup, false)

        return RecipesListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val recipe: Recipe = dataSet[position]
        viewHolder.binding.tvRecipeTitle.text = recipe.title

        viewHolder.binding.root.setOnClickListener{
            itemClickListener?.onItemClick(recipe.id)
        }

        val drawable = try {
            val inputStream = viewHolder.itemView.context.assets.open(recipe.imageUrl)
            inputStream.use { stream ->
                Drawable.createFromStream(stream, null)
            }
        } catch (e: Exception) {
            Log.d("Warning!", "Image no found ${recipe.imageUrl}")
            null
        }
        viewHolder.binding.itemRecipeImageView.setImageDrawable(drawable)

    }

    override fun getItemCount() = dataSet.size

}