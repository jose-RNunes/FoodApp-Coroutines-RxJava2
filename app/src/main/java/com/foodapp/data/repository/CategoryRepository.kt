package com.foodapp.data.repository

import com.foodapp.domain.model.Category

interface CategoryRepository{

    suspend fun fetchCategories(): List<Category>

}