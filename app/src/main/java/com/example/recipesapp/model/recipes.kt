package com.example.recipesapp.model

object STUB{
    private val categories: List<Category> = listOf(
        Category(1, "Бургеры", "burger.png"),
        Category(2,"Десерты", "desert.png"),
        Category(3,"Рыба", "fish.png"),
        Category(4,"Пицца", "pizza.png"),
    )
    fun getCategories() = categories
}