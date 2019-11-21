package com.foodapp.usecases

import com.foodapp.data.repository.CategoryRepository
import com.foodapp.domain.iteractor.CategoryUseCase
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoryUseCaseTest {


    private val categoryRepository: CategoryRepository = mock()
    private lateinit var categoryUseCase: CategoryUseCase

    @Before
    fun setUp() {
        categoryUseCase = CategoryUseCase(categoryRepository)
    }


    @Test
    fun `Test fetchCategories() when success`() = runBlocking {
        //Prepare
        val categories = mockCategories()
        whenever(categoryRepository.fetchCategories())
            .thenReturn(categories)

        whenever(categoryRepository.fetchMealsByCategory(any()))
            .thenReturn(meals())

        //Action
        val result = categoryUseCase.fetchCategories()

        //Test
        Assert.assertEquals(10, result.size)
        Assert.assertEquals(3, result[0].totalMeals)
        Assert.assertEquals(meals(), result[0].meals)
    }

    private fun mockCategories() = (1..10).map {
        Category(
            id = it.toString(),
            category = "Meal $it",
            description = "Meal Description",
            thumb = "url"
        )
    }.toList()

    private fun meals() = listOf(
        Meal(
            id = "001",
            meal = "Beef and Mustard Pie",
            thumb = ""
        ),
        Meal(
            id = "002",
            meal = "Beef Bourguignon",
            thumb = ""
        ),
        Meal(
            id = "003",
            meal = "Minced Beef Pie",
            thumb = ""
        )
    )

}