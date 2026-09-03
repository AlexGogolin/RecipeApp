package com.example.recipesapp.ui.recipes

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.R
import com.example.recipesapp.data.STUB
import com.example.recipesapp.data.STUB.getRecipeById
import com.example.recipesapp.databinding.FragmentListRecipesBinding
import com.example.recipesapp.ui.ARG_RECIPE
import com.example.recipesapp.ui.CATEGORY_ID
import com.example.recipesapp.ui.CATEGORY_IMAGE_URL
import com.example.recipesapp.ui.CATEGORY_NAME
import com.example.recipesapp.ui.recipe.RecipeFragment

class RecipesListFragment : Fragment() {
    private var binding: FragmentListRecipesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListRecipesBinding.inflate(inflater, container, false)
        val b = binding ?: error("binding is null")
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    var id: Int? = null
    var name: String? = null
    var image: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments?.getInt(CATEGORY_ID)
        name = arguments?.getString(CATEGORY_NAME)
        image = arguments?.getString(CATEGORY_IMAGE_URL)
        binding?.tvRecipeTitle?.text = name
        image?.let { safeImage ->
            val drawableImg = try {
                val inputStream = requireContext().assets.open(safeImage)
                inputStream.use { stream ->
                    Drawable.createFromStream(stream, null)
                }
            } catch (e: Exception) {
                Log.d("Warning!", "Image no found $image")
                null
            }
            binding?.ivRecipeImage?.setImageDrawable(drawableImg)
        }
        initRecycler()
    }

    private fun initRecycler() {
        id?.let {
            val recipes = STUB.getRecipesByCategoryId(0)
            val adapter = RecipesListAdapter(recipes)
            adapter.setOnItemClickListener(object : RecipesListAdapter.OnItemClickListener {
                override fun onItemClick(recipeId: Int) {
                    openRecipeByRecipeId(recipeId)
                }
            })
            binding?.rvRecipes?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvRecipes?.adapter = adapter
        }
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
}
