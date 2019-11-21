package com.foodapp.repository

import com.foodapp.data.Service
import com.foodapp.data.dto.CategoriesDto
import com.foodapp.data.dto.CategoryDto
import com.foodapp.data.dto.MealDto
import com.foodapp.data.dto.MealsDto
import com.foodapp.data.repository.CategoryRepository
import com.foodapp.data.repository.CategoryRepositoryImpl
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CategoryRepositoryTest {

    companion object{
        const val CATEGORY_MOCK = "Beef"
        const val ERROR_MSG_MOCK = "Erro inesperado "
    }

    private val service: Service = mock()

    private lateinit var categoryRepository: CategoryRepository

    @Before
    fun setUp() {
        categoryRepository = CategoryRepositoryImpl(service)
    }

    @Test
    fun `Test fetchCategories() when success`() = runBlocking {
        //Prepare
        whenever(service.fetchCategories())
            .thenReturn(mockCategoriesDto())

        //Action
        val result = categoryRepository.fetchCategories()

        //Test
        assertEquals(10, result.size)
        assertEquals(mockCategories(), result)
    }

    @Test
    fun `Test fetchCategories() when error`() = runBlocking {
        //Prepare
        whenever(service.fetchCategories())
            .thenReturn(mockCategoriesDto())

        //Test
        try {
            categoryRepository.fetchCategories()
            fail(ERROR_MSG_MOCK)
        } catch (e: Throwable) {
            assertEquals(e.message, ERROR_MSG_MOCK)
        }
    }

    @Test
    fun `Test fetchMealsByCategory() when success`() = runBlocking{
        //Prepare
        whenever(service.fetchMealsByCategory(CATEGORY_MOCK))
            .thenReturn(mockMealsDto())

        //Action
        val result = categoryRepository.fetchMealsByCategory(CATEGORY_MOCK)

        //Test
        assertEquals(mockMeals(), result)
        assertEquals(10, result.size)
    }

    @Test
    fun `Test fetchMealsByCategory() when error`() = runBlocking {
        //Prepare
        whenever(service.fetchMealsByCategory(CATEGORY_MOCK))
            .thenReturn(mockMealsDto())

        //Test
        try {
            categoryRepository.fetchMealsByCategory(CATEGORY_MOCK)
            fail(ERROR_MSG_MOCK)
        } catch (e: Throwable) {
            assertEquals(e.message, ERROR_MSG_MOCK)
        }

    }

    private fun mockCategoriesDto() = CategoriesDto.Response(
        categories = (1..10).map {
            CategoryDto.Response(
                idCategory = it.toString(),
                strCategory = "Meal $it",
                strCategoryDescription = "Meal Description",
                strCategoryThumb = "url"
            )
        }
    )

    private fun mockCategories() = (1..10).map {
        Category(
            id = it.toString(),
            category = "Meal $it",
            description = "Meal Description",
            thumb = "url"
        )
    }

    private fun mockMealsDto() = MealsDto.Response(
        meals = (1..10).map {
            MealDto.Response(
                idMeal = it.toString(),
                strMeal =  "Meal $it",
                strMealThumb = "url"
            )
        }
    )

    private fun mockMeals() = (1..10).map {
        Meal(
            id = it.toString(),
            meal = "Meal $it",
            thumb = "url"
        )
    }
}