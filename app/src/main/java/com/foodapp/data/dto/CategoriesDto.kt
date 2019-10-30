package com.foodapp.data.dto

sealed class CategoriesDto{

    data class Response(
        val categories: List<CategoryDto.Response>
    ): CategoriesDto()

}