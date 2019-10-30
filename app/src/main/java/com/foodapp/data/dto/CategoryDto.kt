package com.foodapp.data.dto

import com.foodapp.domain.model.Category

sealed class CategoryDto{

    data class Response(
        val idCategory: String,
        val strCategory: String,
        val strCategoryThumb: String,
        val strCategoryDescription: String
    ): CategoryDto(){

        fun toCategory() = Category(
            id = idCategory,
            category = strCategory,
            thumb = strCategoryThumb,
            description = strCategoryDescription
        )
    }

}