package com.foodapp.data

import com.foodapp.data.dto.AreaDto
import com.foodapp.data.dto.CategoriesDto
import com.foodapp.data.dto.MealDto
import com.foodapp.data.dto.MealsDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceRx{
    //Rx
    @GET("categories.php")
    fun fetchCategories(): Single<CategoriesDto.Response>

    @GET("filter.php")
    fun fetchMealsByCategory(@Query("c") category: String): Single<MealsDto<MealDto.Response>>

    @GET("lookup.php?")
    fun fetchMealById(@Query("i") idMeal: String): Single<MealsDto<MealDto.Response>>

    @GET("list.php?a=list")
    fun fetchAreas(): Single<MealsDto<AreaDto.Response>>
}