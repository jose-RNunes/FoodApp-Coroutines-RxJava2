package com.foodapp.presentation.meal

import androidx.lifecycle.MutableLiveData
import com.foodapp.domain.iteractor.coroutines.MealUseCase
import com.foodapp.domain.model.Meal
import com.foodapp.presentation.base.BaseViewModel

class MealViewModel(private val mealUseCase: MealUseCase) : BaseViewModel(){

    val meal =  MutableLiveData<Meal>()

    fun init(meal: Meal){
        initOnce {
            this.meal.value = meal
        }
    }

}