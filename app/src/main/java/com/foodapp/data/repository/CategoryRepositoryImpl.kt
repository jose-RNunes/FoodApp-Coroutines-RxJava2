package com.foodapp.data.repository

import com.foodapp.data.Service
import com.foodapp.domain.model.Category

class CategoryRepositoryImpl(private val service: Service) : CategoryRepository{

    override suspend fun fetchCategories(): List<Category> {
       return service.fetchCategories()
            .categories
            .map { it.toCategory()  }
    }

}