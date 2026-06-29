package com.example.recipesapp.model

object STUB{
    private val categories: List<Category> = listOf(
        Category(1, "Бургеры", "Рецепты всех популярных видов бургеров", "burger.png"),
        Category(2,"Десерты","Самые вкусные рецепты десертов специально для вас", "dessert.png"),
        Category(3,"Рыба", "Печеная, жареная, сушеная, любая рыба на твой вкус", "fish.png"),
        Category(4,"Пицца","Пицца на любой вкус и цвет. Лучшая подборка для тебя", "pizza.png"),
    )
    fun getCategories() = categories
}