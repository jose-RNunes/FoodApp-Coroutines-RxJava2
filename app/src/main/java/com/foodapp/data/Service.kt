package com.foodapp.data

import com.foodapp.data.dto.CategoriesDto
import com.foodapp.data.dto.MealsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Service{

    @GET("categories.php")
    suspend fun fetchCategories(): CategoriesDto.Response

    @GET("filter.php")
    suspend fun fetchMealsByCategory(@Query("c") category: String): MealsDto.Response

}