package com.florinda.store.ui.screens.welcome.welcome

sealed interface WelcomeIntents {
    object GetCurrentUser : WelcomeIntents
}