package com.foodapp.domain.iteractor

import com.foodapp.data.repository.CategoryRepository
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class CategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend fun fetchCategories(): List<Category> {
        return withContext(IO) {
            categoryRepository.fetchCategories()
                .map {
                    val meals = fetchMealsByCategory(category = it.category)
                    it.copy(
                        meals = meals,
                        totalMeals = meals.size
                    )
                }
        }
    }

    suspend fun fetchMealsByCategory(category: String): List<Meal> {
        return withContext(IO) { categoryRepository.fetchMealsByCategory(category) }
    }

}