package com.foodapp

import android.app.Application
import com.foodapp.di.dataModule
import com.foodapp.di.domainModule
import com.foodapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class FoodApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FoodApp)
            androidFileProperties()
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}