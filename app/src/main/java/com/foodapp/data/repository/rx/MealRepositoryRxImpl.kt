package com.foodapp.data.repository.rx


import com.foodapp.data.ServiceRx
import com.foodapp.domain.model.Area
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import io.reactivex.Single

class MealRepositoryRxImpl(private val service: ServiceRx) : MealRepositoryRx {

    override fun fetchCategories(): Single<List<Category>> {
        return service.fetchCategories()
            .map { it.categories.map { c -> c.toCategory() } }
    }

    override fun fetchMealsByCategory(category: String): Single<List<Meal>> {
        return service.fetchMealsByCategory(category)
            .map { it.meals.map { m -> m.toMeal() } }
    }

    override fun fetchMealById(idMeal: String): Single<Meal> {
        return service.fetchMealById(idMeal)
            .map { it.meals.map { m -> m.toMeal() } }
            .toObservable()
            .firstOrError()
            .map { it[0] }

    }

    override fun fetchAreas(): Single<List<Area>> {
        return service.fetchAreas()
            .map { it.meals.map { m -> m.toArea() } }
    }


}