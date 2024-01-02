package com.florinda.di

import com.florinda.data.data_source.remote.ApiService
import com.florinda.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.lazyModule
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@OptIn(KoinExperimentalAPI::class)
val networkModules = lazyModule {

    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BASIC
        }

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
    }


    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    single { provideOkHttpClient() } withOptions {
        createdAtStart()
    }
    single { provideRetrofit(okHttpClient = get()) } withOptions {
        createdAtStart() // create single instance at Koin start
    }
    single { provideApiService(retrofit = get()) } withOptions {
        createdAtStart()
    }

}


