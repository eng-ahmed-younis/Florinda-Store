package com.florinda.store.ui.screens.auth.register

import com.google.firebase.auth.FirebaseUser

data class RegisterState(
    val isLoading: Boolean = false,
    val data: FirebaseUser? = null,
    val error: String? = null
)