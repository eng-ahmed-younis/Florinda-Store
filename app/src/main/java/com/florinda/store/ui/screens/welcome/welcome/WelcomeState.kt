package com.florinda.store.ui.screens.welcome.welcome

import com.google.firebase.auth.FirebaseUser

data class WelcomeState(
    val isLoading: Boolean = false,
    val currentUser: FirebaseUser? = null,
    val error : String? = null
)
