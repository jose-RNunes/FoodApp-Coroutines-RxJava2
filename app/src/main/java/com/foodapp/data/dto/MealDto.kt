package com.foodapp.data.dto

import com.foodapp.domain.model.Meal

sealed class MealDto {

    data class Response(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String,
        val strInstructions: String?,
        val strArea: String?,
        val strTags: String?
    ) : MealDto() {

        fun toMeal() = Meal(
            id = idMeal,
            meal = strMeal,
            thumb = strMealThumb,
            instructions = strInstructions ?: "",
            area = strArea ?: "",
            tags = strTags ?: ""
        )
    }
}