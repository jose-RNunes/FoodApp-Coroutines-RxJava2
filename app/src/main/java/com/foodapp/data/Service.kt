package com.foodapp.data

import com.foodapp.data.dto.AreaDto
import com.foodapp.data.dto.CategoriesDto
import com.foodapp.data.dto.MealDto
import com.foodapp.data.dto.MealsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Service{

    @GET("categories.php")
    suspend fun fetchCategories(): CategoriesDto.Response

    @GET("filter.php")
    suspend fun fetchMealsByCategory(@Query("c") category: String): MealsDto<MealDto.Response>

    @GET("lookup.php?")
    suspend fun fetchMealById(@Query("i") idMeal: String): MealsDto<MealDto.Response>

    @GET("list.php?a=list")
    suspend fun fetchAreas(): MealsDto<AreaDto.Response>

}