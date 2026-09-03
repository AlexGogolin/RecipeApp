package com.example.recipesapp.ui.recipe

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.R
import com.example.recipesapp.data.FAVORITES_FILE
import com.example.recipesapp.data.FAVORITES_LIST
import com.example.recipesapp.data.model.Recipe
import com.example.recipesapp.databinding.FragmentRecipeBinding
import com.example.recipesapp.ui.ARG_RECIPE
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment() {
    private var binding: FragmentRecipeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val b = binding ?: error("binding is null")
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()
        val recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireNotNull(args.getParcelable(ARG_RECIPE, Recipe::class.java))
        } else {
            requireNotNull(args.getParcelable<Recipe>(ARG_RECIPE))
        }

        initUI(recipe)
        initRecycler(recipe)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initRecycler(recipe: Recipe) {
        val ingredientsAdapter = IngredientsAdapter(recipe.ingredients)
        val methodAdapter = MethodAdapter(recipe.method)
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding?.rvIngredients?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvIngredients?.adapter = ingredientsAdapter
        binding?.rvIngredients?.addItemDecoration(divider)
        binding?.rvMethod?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvMethod?.adapter = methodAdapter
        binding?.rvMethod?.addItemDecoration(divider)
        binding?.sbPortions?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                binding?.tvPortions?.text = "$progress"
                ingredientsAdapter.updateIngredients(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun initUI(recipe: Recipe) {
        val isFavorites = getFavorites().contains(recipe.id.toString())
        if (isFavorites) {
            binding?.ibFavoriteBtn?.setImageResource(R.drawable.ic_heart)
        } else {
            binding?.ibFavoriteBtn?.setImageResource(R.drawable.ic_heart_empty)
        }
        binding?.tvRecipeHeader?.text = recipe.title

        binding?.ibFavoriteBtn?.setOnClickListener {
            val favorites = getFavorites()
            val id = recipe.id.toString()
            if (favorites.contains(id)) {
                favorites.remove(id)
                binding?.ibFavoriteBtn?.setImageResource(R.drawable.ic_heart_empty)
            } else {
                favorites.add(id)
                binding?.ibFavoriteBtn?.setImageResource(R.drawable.ic_heart)
            }
            saveFavorites(favorites)
        }
    }

    private fun saveFavorites(favorites: Set<String>) {
        val sharedPrefs =
            requireContext().getSharedPreferences(FAVORITES_FILE, Context.MODE_PRIVATE)
        sharedPrefs.edit()
            .putStringSet(FAVORITES_LIST, favorites)
            .apply()
    }

    private fun getFavorites(): MutableSet<String> {
        val sharedPrefs =
            requireContext().getSharedPreferences(FAVORITES_FILE, Context.MODE_PRIVATE)
        val stored = sharedPrefs.getStringSet(FAVORITES_LIST, emptySet())
        return HashSet(stored ?: emptySet())
    }
}
