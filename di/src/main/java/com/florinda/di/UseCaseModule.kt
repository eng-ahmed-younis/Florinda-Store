package com.florinda.di

import com.florinda.domain.use_case.GetCategoriesUseCase
import com.florinda.domain.use_case.GetOrdersByCategoriesUseCase
import com.florinda.domain.use_case.GetOrdersUseCase
import com.florinda.domain.use_case.auth_use_case.GetCurrentUserUseCase
import com.florinda.domain.use_case.auth_use_case.ResetPasswordUseCase
import com.florinda.domain.use_case.auth_use_case.LoginUseCase
import com.florinda.domain.use_case.auth_use_case.LogOutUseCase
import com.florinda.domain.use_case.auth_use_case.RegisterUseCase
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.lazyModule


@OptIn(KoinExperimentalAPI::class)
val useCaseModules  = lazyModule {
    factoryOf(::GetOrdersUseCase)
    factoryOf(::GetOrdersByCategoriesUseCase)
    factoryOf(::GetCategoriesUseCase)
    factoryOf(::LogOutUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::ResetPasswordUseCase)
    factoryOf(::GetCurrentUserUseCase) 
}



