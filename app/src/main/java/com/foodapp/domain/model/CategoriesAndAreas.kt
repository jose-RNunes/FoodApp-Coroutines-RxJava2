package com.foodapp.domain.model

data class CategoriesAndAreas(
    val areas: List<Area> = emptyList(),
    val categories: List<Category> = emptyList()
)