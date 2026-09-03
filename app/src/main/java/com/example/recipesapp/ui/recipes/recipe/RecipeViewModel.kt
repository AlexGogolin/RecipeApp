package com.example.recipesapp.ui.recipes.recipe

import androidx.lifecycle.ViewModel
import com.example.recipesapp.model.Ingredient
import com.example.recipesapp.model.Recipe

data class RecipeState(
    val recipe: Recipe? = null,
    val portionsCount: Int = 1,
    val isFavorite: Boolean = false,
    val ingredients: List<Ingredient> = emptyList(),
    val method: List<String> = emptyList(),
)

class RecipeViewModel : ViewModel()
