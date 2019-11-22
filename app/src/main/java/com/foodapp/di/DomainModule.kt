package com.foodapp.di

import com.foodapp.domain.iteractor.MealUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { MealUseCase(get()) }
}