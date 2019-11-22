package com.foodapp.data.repository

import com.foodapp.data.Service
import com.foodapp.domain.model.Area
import com.foodapp.domain.model.CategoriesAndAreas
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MealRepositoryImpl(private val service: Service) : MealRepository{

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

    private suspend fun fetchAreas(): List<Area> {
        return service.fetchAreas()
            .meals
            .map { it.toArea() }
    }

    override suspend fun fetchCategoriesAndAreas(): CategoriesAndAreas {
        return coroutineScope {
            val categories = async { fetchCategories() }
            val areas = async { fetchAreas() }
            CategoriesAndAreas(
                categories = categories.await(),
                areas = areas.await()
            )
        }
    }

}