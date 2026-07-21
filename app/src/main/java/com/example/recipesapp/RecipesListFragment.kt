package com.example.recipesapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.databinding.FragmentListRecipesBinding
import com.example.recipesapp.model.STUB
import com.example.recipesapp.model.STUB.getRecipeById

const val CATEGORY_ID = "ARG_CATEGORY_ID"
const val CATEGORY_NAME = "ARG_CATEGORY_NAME"
const val CATEGORY_IMAGE_URL = "ARG_CATEGORY_IMAGE_URL"

const val ARG_RECIPE = "ARG_RECIPE"

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