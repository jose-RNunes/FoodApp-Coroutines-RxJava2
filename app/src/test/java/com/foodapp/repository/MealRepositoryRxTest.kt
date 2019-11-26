package com.foodapp.repository

import com.foodapp.data.Service
import com.foodapp.data.ServiceRx
import com.foodapp.data.dto.CategoriesDto
import com.foodapp.data.dto.CategoryDto
import com.foodapp.data.repository.coroutines.MealRepository
import com.foodapp.data.repository.coroutines.MealRepositoryImpl
import com.foodapp.data.repository.rx.MealRepositoryRx
import com.foodapp.data.repository.rx.MealRepositoryRxImpl
import com.foodapp.domain.model.Category
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class MealRepositoryRxTest {


    companion object {
        const val CATEGORY_MOCK = "Beef"
        const val ERROR_MSG_MOCK = "Erro inesperado "
    }

    private val serviceMock: ServiceRx = mock()

    private lateinit var mealRepository: MealRepositoryRx

    @Before
    fun setUp() {
        mealRepository = MealRepositoryRxImpl(serviceMock)
    }

    @Test
    fun `Test fetchCategories() when success`(){
        //Prepare
        whenever(serviceMock.fetchCategories())
            .thenReturn(Single.just(mockCategoriesDto()))

        //Action
        val testObserver = mealRepository.fetchCategories().test()

        //Test
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(mockCategories())
        testObserver.assertValue { it.size == 10 }
    }

    @Test
    fun `Test fetchCategories() when error`(){
        //Prepare
        val exception = Exception(ERROR_MSG_MOCK)

        whenever(serviceMock.fetchCategories())
            .thenReturn(Single.error(exception))

        //Action
        val testObserver = mealRepository.fetchCategories().test()

        //Test
        testObserver.assertError(exception)
        testObserver.assertErrorMessage(exception.message)
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

}