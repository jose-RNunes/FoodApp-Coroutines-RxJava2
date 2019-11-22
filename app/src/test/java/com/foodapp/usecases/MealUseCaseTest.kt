package com.foodapp.usecases

import com.foodapp.data.repository.MealRepository
import com.foodapp.domain.iteractor.MealUseCase
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MealUseCaseTest {

    private val mealRepositoryMock: MealRepository = mock()

    private lateinit var mealUseCase: MealUseCase

    @Before
    fun setUp() {
        mealUseCase = MealUseCase(mealRepositoryMock)
    }

    @Test
    fun `Test fetchCategories() when success`() = runBlocking {
        //Prepare
        val categories = mockCategories()
        whenever(mealRepositoryMock.fetchCategories())
            .thenReturn(categories)

        whenever(mealRepositoryMock.fetchMealsByCategory(any()))
            .thenReturn(meals())

        //Action
        val result = mealUseCase.fetchCategories()

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
    }

    private fun meals() = listOf(
        Meal(
            id = "001",
            meal = "Beef and Mustard Pie",
            thumb = "",
            tags = "",
            instructions = "",
            area = ""
        ),
        Meal(
            id = "002",
            meal = "Beef Bourguignon",
            thumb = "",
            tags = "",
            instructions = "",
            area = ""
        ),
        Meal(
            id = "003",
            meal = "Minced Beef Pie",
            thumb = "",
            tags = "",
            instructions = "",
            area = ""
        )
    )

}