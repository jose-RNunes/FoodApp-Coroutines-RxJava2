package com.foodapp.data.repository.coroutines

import com.foodapp.data.Service
import com.foodapp.domain.model.Area
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal

class MealRepositoryImpl(private val service: Service) : MealRepository {

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

    override suspend fun fetchMealById(idMeal: String): Meal? {
        return service.fetchMealById(idMeal)
            .meals
            .map { it.toMeal() }
            .firstOrNull()
    }

    override suspend fun fetchAreas(): List<Area> {
        return service.fetchAreas()
            .meals
            .map { it.toArea() }
    }


}