package com.foodapp.di

import com.foodapp.domain.iteractor.CategoryUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { CategoryUseCase(get()) }
}