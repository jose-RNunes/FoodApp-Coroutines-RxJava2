package com.foodapp.repository

import com.foodapp.data.Service
import com.foodapp.data.dto.*
import com.foodapp.data.repository.MealRepository
import com.foodapp.data.repository.MealRepositoryImpl
import com.foodapp.domain.model.Area
import com.foodapp.domain.model.CategoriesAndAreas
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MealRepositoryTest {

    companion object {
        const val CATEGORY_MOCK = "Beef"
        const val ERROR_MSG_MOCK = "Erro inesperado "
    }

    private val serviceMock: Service = mock()

    private lateinit var mealRepository: MealRepository

    @Before
    fun setUp() {
        mealRepository = MealRepositoryImpl(serviceMock)
    }

    @Test
    fun `Test fetchCategories() when success`() = runBlocking {
        //Prepare
        whenever(serviceMock.fetchCategories())
            .thenReturn(mockCategoriesDto())

        //Action
        val result = mealRepository.fetchCategories()

        //Test
        assertEquals(10, result.size)
        assertEquals(mockCategories(), result)
    }

    @Test
    fun `Test fetchCategories() when error`() = runBlocking {
        //Prepare
        whenever(serviceMock.fetchCategories())
            .thenReturn(mockCategoriesDto())

        //Action
        val result = mealRepository.fetchCategories()

        //Test
        try {
            result[0]
            fail(ERROR_MSG_MOCK)
        } catch (e: Throwable) {
            assertEquals(e.message, ERROR_MSG_MOCK)
        }
    }


    @Test
    fun `Test fetchMealsByCategory() when success`() = runBlocking {
        //Prepare
        whenever(serviceMock.fetchMealsByCategory(CATEGORY_MOCK))
            .thenReturn(mockMealsDto())

        //Action
        val result = mealRepository.fetchMealsByCategory(CATEGORY_MOCK)

        //Test
        assertEquals(mockMeals(), result)
        assertEquals(10, result.size)
    }

    @Test
    fun `Test fetchMealsByCategory() when error`() = runBlocking {
        //Prepare
        whenever(serviceMock.fetchMealsByCategory(CATEGORY_MOCK))
            .thenReturn(mockMealsDto())

        //Action
        val result = mealRepository.fetchMealsByCategory(CATEGORY_MOCK)

        //Test
        try {
            result[0]
            fail(ERROR_MSG_MOCK)
        } catch (e: Throwable) {
            assertEquals(e.message, ERROR_MSG_MOCK)
        }
    }

    @Test
    fun `Test fetchCategoriesAndAreas() when success`() = runBlocking {
        //Prepare
        val categoriesAndAreas = CategoriesAndAreas(
            categories = mockCategories(),
            areas = mockAreas()
        )

        whenever(serviceMock.fetchCategories())
            .thenReturn(mockCategoriesDto())

        whenever(serviceMock.fetchAreas())
            .thenReturn(mockAreasDto())

        //Action
        val result = mealRepository.fetchCategoriesAndAreas()

        //Test
        assertEquals(categoriesAndAreas, result)
    }

    @Test
    fun `Test fetchCategoriesAndAreas() when error`() = runBlocking {
        //Prepare
        whenever(serviceMock.fetchCategories())
            .thenReturn(mockCategoriesDto())

        whenever(serviceMock.fetchAreas())
            .thenReturn(mockAreasDto())

        //Action
        val result = mealRepository.fetchCategoriesAndAreas()

        //Test
        try {
            result.areas
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

    private fun mockMealsDto() = MealsDto(
        meals = (1..10).map {
            MealDto.Response(
                idMeal = it.toString(),
                strMeal = "Meal $it",
                strMealThumb = "url",
                strArea = null,
                strInstructions = null,
                strTags = null
            )
        }
    )

    private fun mockMeals() = (1..10).map {
        Meal(
            id = it.toString(),
            meal = "Meal $it",
            thumb = "url",
            area = "",
            instructions = "",
            tags = ""
        )
    }

    private fun mockAreasDto() = MealsDto(
        meals = (1..5).map {
            AreaDto.Response(strArea = "Area $it")
        }
    )

    private fun mockAreas() = (1..5).map {
        Area(area = "Area $it")
    }

}