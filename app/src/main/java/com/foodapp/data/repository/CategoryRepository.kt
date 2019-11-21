package com.foodapp.data.repository

import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal

interface CategoryRepository{

    suspend fun fetchCategories(): List<Category>

    suspend fun fetchMealsByCategory(category: String): List<Meal>

}