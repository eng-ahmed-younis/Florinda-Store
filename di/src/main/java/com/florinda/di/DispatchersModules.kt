package com.florinda.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule
import org.koin.dsl.module


@OptIn(KoinExperimentalAPI::class)
val dispatcherModules = lazyModule {

    single(named(DispatchersAnnotations.DefaultDispatcher)) { Dispatchers.Default }
    single(named(DispatchersAnnotations.IoDispatcher)) { Dispatchers.IO }
    single(named(DispatchersAnnotations.MainDispatcher)) { Dispatchers.Main }
    single(named(DispatchersAnnotations.ImmediateDispatcher)) { Dispatchers.Main.immediate }
}



enum class DispatchersAnnotations {
    IoDispatcher,
    MainDispatcher,
    DefaultDispatcher,
    ImmediateDispatcher,
}