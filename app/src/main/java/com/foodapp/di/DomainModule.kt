package com.foodapp.di

import com.foodapp.domain.iteractor.coroutines.MealUseCase
import com.foodapp.domain.iteractor.rx.MealUseCaseRx
import org.koin.dsl.module

val domainModule = module {

    factory { MealUseCase(mealRepository = get()) }

    factory { MealUseCaseRx(mealRepository = get()) }
}