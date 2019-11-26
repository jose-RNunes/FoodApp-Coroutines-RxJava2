package com.foodapp.presentation.category.rx


import androidx.lifecycle.MutableLiveData
import com.foodapp.domain.iteractor.rx.MealUseCaseRx
import com.foodapp.domain.model.Category
import com.foodapp.domain.model.Meal
import com.foodapp.presentation.base.BaseViewModel
import com.foodapp.presentation.ui.custom.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers

class CategoryViewModelRx(private val mealUseCase: MealUseCaseRx) : BaseViewModel() {

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
        showLoading(true)
        compositeDisposable.add(
            mealUseCase.fetchCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        showLoading(false)
                        categories.value = it
                    },
                    {
                        presentation.value = Presentation(error = it.message)
                    }
                )
        )
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