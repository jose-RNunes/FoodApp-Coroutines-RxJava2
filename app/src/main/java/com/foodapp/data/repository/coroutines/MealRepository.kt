package com.foodapp.data.repository.coroutines

import com.foodapp.domain.model.Area
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal

interface MealRepository {

    suspend fun fetchCategories(): List<Category>

    suspend fun fetchMealsByCategory(category: String): List<Meal>

    suspend fun fetchMealById(idMeal: String): Meal?

    suspend fun fetchAreas(): List<Area>

}