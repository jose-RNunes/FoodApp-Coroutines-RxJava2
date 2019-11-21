package com.foodapp.presentation.category

import androidx.lifecycle.MutableLiveData
import com.foodapp.domain.iteractor.CategoryUseCase
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.foodapp.presentation.base.BaseViewModel
import com.foodapp.presentation.ui.custom.SingleLiveData
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryUseCase: CategoryUseCase) : BaseViewModel() {

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
            screen.value = Screen.InitCategory
            fetchCategories()
        }
    }

    fun fetchCategories() {
        uiScope.launch {
            showLoading(true)
            runCatching {
                categories.value = categoryUseCase.fetchCategories()
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