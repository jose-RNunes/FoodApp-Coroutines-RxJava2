package com.foodapp.di

import com.foodapp.presentation.category.CategoryAdapter
import com.foodapp.presentation.category.coroutines.CategoryViewModel
import com.foodapp.presentation.category.rx.CategoryViewModelRx
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { CategoryViewModel(get()) }

    viewModel { CategoryViewModelRx(get()) }

    factory { CategoryAdapter() }
}