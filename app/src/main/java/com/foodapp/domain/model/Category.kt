package com.foodapp.domain.model

data class Category(
    val id: String,
    val category: String,
    val thumb: String,
    val description: String,
    val meals: List<Meal> = emptyList(),
    val totalMeals: Int = 0
)