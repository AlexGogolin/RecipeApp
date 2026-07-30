package com.example.recipesapp.model

import kotlin.collections.emptyList

object STUB{
    private val categories: List<Category> = listOf(
        Category(1, "Бургеры", "Рецепты всех популярных видов бургеров", "burger.png"),
        Category(2,"Десерты","Самые вкусные рецепты десертов специально для вас", "dessert.png"),
        Category(3,"Рыба", "Печеная, жареная, сушеная, любая рыба на твой вкус", "fish.png"),
        Category(4,"Пицца","Пицца на любой вкус и цвет. Лучшая подборка для тебя", "pizza.png"),
    )
    private val burgerRecipes: List<Recipe> = listOf(
        Recipe(
            0,
            "Классический бургер с говядиной",
            ingredients = listOf(
                Ingredient("0.5", "кг", "говяжий фарш"),
                Ingredient("1.0", "шт", "луковица, мелко нарезанная"),
                Ingredient("2.0", "зубч", "чеснок, измельченный"),
                Ingredient("4.0", "шт", "булочки для бургера"),
                Ingredient("4.0", "шт", "листа салата"),
                Ingredient("1.0", "шт", "помидор, нарезанный кольцами"),
                Ingredient("2.0", "ст. л", "горчица"),
                Ingredient("2.0", "ст. л", "кетчуп"),
                Ingredient("по вкусу", "", "соль и черный перец"),

            ),
            method =listOf(
                "1. В глубокой миске смешайте говяжий фарш, лук, чеснок, соль и перец. Разделите фарш на 4 равные части и сформируйте котлеты.",
                "2. Разогрейте сковороду на среднем огне. Обжаривайте котлеты с каждой стороны в течение 4-5 минут или до желаемой степени прожарки.",
                "3. В то время как котлеты готовятся, подготовьте булочки. Разрежьте их пополам и обжарьте на сковороде до золотистой корочки.",
                "4. Смазать нижние половинки булочек горчицей и кетчупом, затем положите лист салата, котлету, кольца помидора и закройте верхней половинкой булочки.",
                "5. Подавайте бургеры горячими с картофельными чипсами или картофельным пюре."
            ),
            imageUrl = "burger.png"
        )
    )

    fun getCategories() = categories

    fun getRecipesByCategoryId(categoryId: Int): List<Recipe>{
        if (categoryId == 0){
            return  burgerRecipes
        } else  return emptyList()
    }

    fun getRecipeById(recipeId: Int): Recipe?{
        return burgerRecipes.find {it.id == recipeId}
    }
}