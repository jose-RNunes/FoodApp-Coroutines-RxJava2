package com.foodapp.presentation.category

import androidx.lifecycle.MutableLiveData
import com.foodapp.domain.iteractor.CategoryUseCase
import com.foodapp.domain.model.Category
import com.foodapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryUseCase: CategoryUseCase) : BaseViewModel(){

    data class Presentation(
        val loading: Boolean = false,
        val error: String? = null
    ){
        fun showScreen() = !loading && error.isNullOrBlank()

        fun showError() = error != null
    }

    val presentation = MutableLiveData<Presentation>()

    val categories = MutableLiveData<List<Category>>()

    fun init(){
        initOnce {
            fetchCategories()
        }
    }

    fun fetchCategories(){
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

    private fun showLoading(show: Boolean){
        presentation.value = Presentation(loading = show)
    }

}