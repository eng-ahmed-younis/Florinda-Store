package com.florinda.di

import com.florinda.data.data_source.local.dataStore.DataStoreImpl
import com.florinda.data.data_source.remote.firebase.FirebaseAuthenticator
import com.florinda.domain.repository.Authenticator
import com.florinda.domain.repository.IPreferenceDataStoreAPI
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val appModule = lazyModule {


    single { FirebaseAuth.getInstance() }
    single<IPreferenceDataStoreAPI> { DataStoreImpl(androidContext()) }
    singleOf(::FirebaseAuthenticator) bind Authenticator::class

}



