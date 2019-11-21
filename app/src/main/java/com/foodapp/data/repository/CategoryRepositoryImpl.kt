package com.foodapp.data.repository

import com.foodapp.data.Service
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal

class CategoryRepositoryImpl(private val service: Service) : CategoryRepository {

    override suspend fun fetchCategories(): List<Category> {
        return service.fetchCategories()
            .categories
            .map { it.toCategory() }
    }

    override suspend fun fetchMealsByCategory(category: String): List<Meal> {
        return service.fetchMealsByCategory(category)
            .meals
            .map { it.toMeal() }
    }

}