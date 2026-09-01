package com.example.recipesapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.databinding.FragmentFavoritesBinding
import com.example.recipesapp.model.STUB
import com.example.recipesapp.model.STUB.getRecipeById

class FavoritesFragment : Fragment() {

    private var binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val b = binding ?: error("binding is null")
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initRecycler() {
        val favoriteIds = getFavorites().mapNotNull { it.toIntOrNull() }.toSet()
        val recipes = STUB.getRecipesByIds(favoriteIds)
        val isEmpty = recipes.isEmpty()
        binding?.tvFavoritesEmpty?.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding?.rvFavorites?.visibility = if (isEmpty) View.GONE else View.VISIBLE

        val adapter = RecipesListAdapter(recipes)
        adapter.setOnItemClickListener(object : RecipesListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
        binding?.rvFavorites?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvFavorites?.adapter = adapter
    }

    private fun openRecipeByRecipeId(recipeId: Int) {
        val recipe = getRecipeById(recipeId)
        val bundle = bundleOf(ARG_RECIPE to recipe)
        val recipeItem = RecipeFragment()
        recipeItem.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, recipeItem)
            .addToBackStack(null)
            .commit()
    }

    private fun getFavorites(): MutableSet<String> {
        val sharedPrefs =
            requireContext().getSharedPreferences(FAVORITES_FILE, Context.MODE_PRIVATE)
        val stored = sharedPrefs.getStringSet(FAVORITES_LIST, emptySet())
        return HashSet(stored ?: emptySet())
    }
}
