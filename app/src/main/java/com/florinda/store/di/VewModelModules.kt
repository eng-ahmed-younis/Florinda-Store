package com.florinda.store.di

import com.florinda.di.DispatchersAnnotations
import com.florinda.store.ui.screens.auth.login.LoginViewModel
import com.florinda.store.ui.screens.bottom.home.HomeViewModel
import com.florinda.store.ui.screens.orders.OrdersViewModel
import com.florinda.store.ui.screens.on_boarding.OnBoardingViewModel
import com.florinda.store.ui.screens.auth.register.RegistrationViewModel
import com.florinda.store.ui.screens.search.SearchViewModel
import com.florinda.store.ui.screens.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule


@OptIn(KoinExperimentalAPI::class)
val viewModelModules = lazyModule {
    viewModel { HomeViewModel(get(), get() , get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { OnBoardingViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { OrdersViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { SearchViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { WelcomeViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { LoginViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
    viewModel { RegistrationViewModel(get(), get(named(DispatchersAnnotations.IoDispatcher))) }
}