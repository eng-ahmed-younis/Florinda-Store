package com.florinda.di

import com.florinda.data.repository.AuthRepositoryImpl
import com.florinda.data.repository.OrderRepositoryImpl
import com.florinda.domain.repository.AuthRepository
import com.florinda.domain.repository.Authenticator
import com.florinda.domain.repository.OrderRepository
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.bind
import org.koin.dsl.lazyModule
import org.koin.dsl.module

/**
 * Your constructor is filled automatically with all get().
 */
@OptIn(KoinExperimentalAPI::class)
val repositoryModules = lazyModule {
    singleOf(::OrderRepositoryImpl) bind OrderRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}