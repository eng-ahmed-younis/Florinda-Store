package com.florinda.store.ui.screens.auth.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val isLoading : Boolean = false,
    val data : FirebaseUser? = null,
    val error : String? = null
)
