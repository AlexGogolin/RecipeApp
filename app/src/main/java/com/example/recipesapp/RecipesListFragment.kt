package com.example.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipesapp.databinding.FragmentListRecipesBinding
import com.example.recipesapp.model.STUB

class RecipesListFragment: Fragment() {
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
        id = arguments?.getInt("ARG_CATEGORY_ID")
        name = arguments?.getString("ARG_CATEGORY_NAME")
        image = arguments?.getString("ARG_CATEGORY_IMAGE_URL")
        initRecycler()
    }

    private fun initRecycler() {
        
    }
}