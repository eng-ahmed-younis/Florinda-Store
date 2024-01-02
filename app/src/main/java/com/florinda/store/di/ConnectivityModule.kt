package com.florinda.store.di

import com.florinda.store.ui.utils.ConnectivityObserver
import com.florinda.store.ui.utils.NetworkConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val connectivityModule  = lazyModule {
    single<ConnectivityObserver> {  NetworkConnectivityObserver(androidContext()) }
}