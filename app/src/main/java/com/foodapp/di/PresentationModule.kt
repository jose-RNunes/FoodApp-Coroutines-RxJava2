package com.foodapp.di

import com.foodapp.presentation.category.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { CategoryViewModel(get()) }
}