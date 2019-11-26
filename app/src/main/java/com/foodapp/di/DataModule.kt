package com.foodapp.di

import com.foodapp.BuildConfig
import com.foodapp.data.Service
import com.foodapp.data.RetrofitConfig
import com.foodapp.data.ServiceRx
import com.foodapp.data.repository.coroutines.MealRepository
import com.foodapp.data.repository.coroutines.MealRepositoryImpl
import com.foodapp.data.repository.rx.MealRepositoryRx
import com.foodapp.data.repository.rx.MealRepositoryRxImpl
import com.foodapp.di.Properties.BASE_URL
import org.koin.dsl.module

val dataModule = module {

    single<Service> {
        RetrofitConfig.create(
            baseUrl = getProperty(BASE_URL),
            enableLogging = BuildConfig.DEBUG
        )
    }

    single<ServiceRx> {
        RetrofitConfig.create(
            baseUrl = getProperty(BASE_URL),
            enableLogging = BuildConfig.DEBUG
        )
    }

    factory<MealRepository> {
        MealRepositoryImpl(
            service = get()
        )
    }

    factory<MealRepositoryRx> {
        MealRepositoryRxImpl(
            service = get()
        )
    }

}