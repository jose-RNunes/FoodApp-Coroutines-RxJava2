package com.foodapp.data


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    const val TIMEOUT = 40L

    inline fun <reified T> create(
        baseUrl: String,
        enableLogging: Boolean = true
    ): T {

        val clientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)


        if (enableLogging) {
            clientBuilder.addInterceptor(getHttpLoggingInterceptor())
        }

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .baseUrl(baseUrl)
            .build()
            .create(T::class.java)
    }


    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}