package com.florinda.store.ui.screens.auth.register

sealed interface RegisterIntents{
    data class RegisterNewAccount (val email : String , val password : String): RegisterIntents
}

