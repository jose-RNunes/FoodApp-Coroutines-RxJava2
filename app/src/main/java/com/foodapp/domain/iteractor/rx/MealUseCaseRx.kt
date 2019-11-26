package com.foodapp.domain.iteractor.rx


import com.foodapp.data.repository.rx.MealRepositoryRx
import com.foodapp.domain.model.Area
import com.foodapp.domain.model.CategoriesAndAreas
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MealUseCaseRx(private val mealRepository: MealRepositoryRx) {

    fun fetchCategories(): Single<List<Category>> {
        return mealRepository.fetchCategories()
            .flattenAsObservable { it }
            .flatMap { fetchMealsByCategory(it).toObservable() }
            .toList()
            .subscribeOn(Schedulers.io())
    }

    private fun fetchMealsByCategory(category: Category): Single<Category> {
        return mealRepository.fetchMealsByCategory(category.category)
            .map {
                category.copy(
                    meals = it,
                    totalMeals = it.size
                )
            }
    }

    fun fetchMealById(idMeal: String): Single<Meal> {
        return mealRepository.fetchMealById(idMeal)
            .subscribeOn(Schedulers.io())
    }

    fun fetchCategoriesAndAreas(): Single<CategoriesAndAreas> {
        return Observable
            .zip(
                mealRepository.fetchCategories().toObservable(),
                mealRepository.fetchAreas().toObservable(),
                BiFunction<List<Category>, List<Area>, CategoriesAndAreas>
                { categories, areas ->
                    return@BiFunction CategoriesAndAreas(areas, categories)
                }
            ).firstOrError()
            .subscribeOn(Schedulers.io())
    }

}