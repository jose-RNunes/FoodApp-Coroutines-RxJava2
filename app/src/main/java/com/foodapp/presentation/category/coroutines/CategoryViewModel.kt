package com.foodapp.presentation.category.coroutines

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.foodapp.domain.iteractor.coroutines.MealUseCase
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.foodapp.presentation.base.BaseViewModel
import com.foodapp.presentation.ui.custom.SingleLiveData
import kotlinx.coroutines.launch
import java.lang.Exception

class CategoryViewModel(private val mealUseCase: MealUseCase) : BaseViewModel() {

    data class Presentation(
        val loading: Boolean = false,
        val error: String? = null
    ) {
        fun showScreen() = !loading && error.isNullOrBlank()

        fun showError() = error != null
    }

    sealed class Screen {
        object InitCategory : Screen()
        object GoToCategoryDetails : Screen()
    }

    val screen = SingleLiveData<Screen>()

    val presentation = MutableLiveData<Presentation>()

    val categories = MutableLiveData<List<Category>>()

    val meals = MutableLiveData<List<Meal>>()

    private lateinit var categorySelected: Category

    fun init() {
        initOnce {
            uiScope.launch {
                try {
                    val result = mealUseCase.fetchCategoriesAndAreas()

                    Log.i("TAG", "Result $result")
                } catch (e: Exception) {
                    Log.e("TAG", "Error ${e.message}")
                }
            }

            screen.value =
                Screen.InitCategory
            fetchCategories()
        }
    }

    fun fetchCategories() {
        uiScope.launch {
            showLoading(true)
            runCatching {
                categories.value = mealUseCase.fetchCategories()
                showLoading(false)
            }.onFailure {
                presentation.value = Presentation(error = it.message)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        presentation.value = Presentation(loading = show)
    }

    fun selectedCategory(category: String) {
        categories.value?.let {
            meals.value = it.firstOrNull { c -> c.category == category }
                ?.meals
                ?: emptyList()
        }
    }

}