package com.foodapp.di

import com.foodapp.BuildConfig
import com.foodapp.data.Service
import com.foodapp.data.RetrofitConfig
import com.foodapp.data.repository.CategoryRepository
import com.foodapp.data.repository.CategoryRepositoryImpl
import com.foodapp.di.Properties.BASE_URL
import org.koin.dsl.module

val dataModule = module {

    single<Service> {
        RetrofitConfig.create(
            baseUrl = getProperty(BASE_URL),
            enableLogging = BuildConfig.DEBUG
        )
    }

    factory<CategoryRepository>{ CategoryRepositoryImpl(get()) }

}