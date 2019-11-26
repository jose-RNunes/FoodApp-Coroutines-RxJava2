package com.foodapp.data.repository.rx

import com.foodapp.domain.model.Area
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import io.reactivex.Single

interface MealRepositoryRx {

    fun fetchCategories(): Single<List<Category>>

    fun fetchMealsByCategory(category: String): Single<List<Meal>>

    fun fetchMealById(idMeal: String): Single<Meal>

    fun fetchAreas(): Single<List<Area>>

}