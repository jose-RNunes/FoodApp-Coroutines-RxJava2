package com.foodapp.domain.iteractor

import com.foodapp.data.repository.CategoryRepository
import com.foodapp.domain.model.Category

class CategoryUseCase(private val categoryRepository: CategoryRepository) {

    suspend fun fetchCategories(): List<Category> {
        return categoryRepository.fetchCategories()
    }


}