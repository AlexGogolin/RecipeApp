package com.example.recipesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.ItemMethodBinding

class MethodAdapter(private val dataSet: List<String>):
    RecyclerView.Adapter<MethodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMethodBinding.inflate(inflater, parent, false)

        return MethodAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val method = dataSet[position]
        holder.binding.tvMethodName.text = method
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(val binding: ItemMethodBinding) : RecyclerView.ViewHolder(binding.root)


}