package com.example.recipesapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.databinding.FragmentRecipeBinding
import com.example.recipesapp.model.Recipe
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {
    private var binding: FragmentRecipeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val b = binding ?: error("binding is null")
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe: Recipe? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            arguments?.getParcelable<Recipe>(ARG_RECIPE)
        }
        if (recipe != null){
            initUI(recipe)
            initRecycler(recipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initRecycler(recipe: Recipe) {
        val ingredientsAdapter = IngredientsAdapter(recipe.ingredients)
        val methodAdapter = MethodAdapter(recipe.method)
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding?.rvIngredients?.adapter = ingredientsAdapter
        binding?.rvIngredients?.addItemDecoration(divider)
        binding?.rvMethod?.adapter = methodAdapter
        binding?.rvMethod?.addItemDecoration(divider)
    }

    private fun initUI(recipe: Recipe){
        binding?.tvRecipeHeader?.text = recipe.title
    }
}