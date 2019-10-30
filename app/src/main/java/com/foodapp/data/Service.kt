package com.foodapp.data

import com.foodapp.data.dto.CategoriesDto
import retrofit2.http.GET

interface Service{

    @GET("categories.php")
    suspend fun fetchCategories(): CategoriesDto.Response

}