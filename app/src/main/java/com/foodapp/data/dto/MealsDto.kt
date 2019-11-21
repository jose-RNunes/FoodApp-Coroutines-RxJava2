package com.foodapp.data.dto

sealed class MealsDto {

    data class Response(
        val meals: List<MealDto.Response>
    ): MealsDto()
}