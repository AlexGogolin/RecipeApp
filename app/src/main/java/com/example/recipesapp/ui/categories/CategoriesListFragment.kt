package com.example.recipesapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.R
import com.example.recipesapp.data.STUB
import com.example.recipesapp.databinding.FragmentListCategoriesBinding
import com.example.recipesapp.ui.CATEGORY_ID
import com.example.recipesapp.ui.CATEGORY_IMAGE_URL
import com.example.recipesapp.ui.CATEGORY_NAME
import com.example.recipesapp.ui.recipes.recipesList.RecipesListFragment

class CategoriesListFragment : Fragment() {

    private var binding: FragmentListCategoriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListCategoriesBinding.inflate(inflater, container, false)
        val b = binding ?: error("binding is null")
        return b.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val categories = STUB.getCategories()
        val adapter = CategoriesListAdapter(categories)
        adapter.setOnItemClickListener(object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(categoryId: Int) {
                openRecipesByCategoryId(categoryId)
            }
        })
        binding?.rvCategories?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvCategories?.adapter = adapter
    }

    private fun openRecipesByCategoryId(categoryId: Int) {
        val categories = STUB.getCategories()
        val category = categories.find { it.id == categoryId }
        val categoryName = category?.title
        val categoryImageUrl = category?.imageUrl
        val bundle = Bundle().apply {
            putInt(CATEGORY_ID, categoryId)
            putString(CATEGORY_NAME, categoryName)
            putString(CATEGORY_IMAGE_URL, categoryImageUrl)
        }
        val recipeFragment = RecipesListFragment()
        recipeFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, recipeFragment)
            .addToBackStack(null)
            .commit()
    }
}
