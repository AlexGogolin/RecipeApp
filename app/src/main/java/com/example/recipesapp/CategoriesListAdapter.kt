package com.example.recipesapp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import com.example.recipesapp.databinding.ItemCategoryBinding
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.model.Category


class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCategoryBinding.inflate(inflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category: Category = dataSet[position]
        viewHolder.binding.tvCategoriesTitle.text = category.title
        viewHolder.binding.tvCategoriesDescr.text = category.description

        val drawable = try {
            val inputStream = viewHolder.itemView.context.assets.open(category.imageUrl)
            inputStream.use { stream ->
                Drawable.createFromStream(stream, null)
            }
        } catch (e: Exception) {
            Log.d("Warning!", "Image no found ${category.imageUrl}")
            null
        }
        viewHolder.binding.itemImageView.setImageDrawable(drawable)

    }

    override fun getItemCount() = dataSet.size

}