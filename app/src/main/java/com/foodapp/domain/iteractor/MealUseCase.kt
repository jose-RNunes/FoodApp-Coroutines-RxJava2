package com.foodapp.domain.iteractor

import com.foodapp.data.repository.MealRepository
import com.foodapp.domain.model.CategoriesAndAreas
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class MealUseCase(private val mealRepository: MealRepository) {

    suspend fun fetchCategories(): List<Category> {
        return withContext(IO) {
            mealRepository.fetchCategories()
                .map {
                    val meals = mealRepository.fetchMealsByCategory(category = it.category)
                    it.copy(
                        meals = meals,
                        totalMeals = meals.size
                    )
                }
        }
    }

    suspend fun fetchMealById(idMeal: String): Meal? {
        return withContext(IO) {
            mealRepository.fetchMealById(idMeal)
        }
    }

    suspend fun fetchMealsByCategory(category: String): List<Meal> {
        return withContext(IO) {
            mealRepository.fetchMealsByCategory(category)
        }
    }

    suspend fun fetchCategoriesAndAreas(): CategoriesAndAreas {
        return mealRepository.fetchCategoriesAndAreas()
    }

}