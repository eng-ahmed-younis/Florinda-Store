package com.florinda.store.ui.screens.auth.login

sealed interface LoginIntents {
    data class LoginUser(val email : String , val password : String) : LoginIntents
}

