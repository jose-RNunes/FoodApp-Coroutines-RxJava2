package com.foodapp.data.dto

import com.foodapp.domain.model.Meal

sealed class MealDto {

    data class Response(
        val strMeal: String,
        val strMealThumb: String,
        val idMeal: String
    ) : MealDto() {

        fun toMeal() = Meal(
            id = idMeal,
            meal = strMeal,
            thumb = strMealThumb
        )
    }
}