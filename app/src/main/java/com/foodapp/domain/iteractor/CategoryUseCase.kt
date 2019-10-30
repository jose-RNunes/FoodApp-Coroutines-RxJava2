package com.foodapp.domain.iteractor

import com.foodapp.data.repository.CategoryRepository
import com.foodapp.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryUseCase (private val categoryRepository: CategoryRepository){

    suspend fun fetchCategories(): List<Category>{
       return withContext(Dispatchers.IO) {
           categoryRepository.fetchCategories()
       }
    }

}