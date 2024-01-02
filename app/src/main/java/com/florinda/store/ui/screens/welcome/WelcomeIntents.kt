package com.florinda.store.ui.screens.welcome

sealed interface WelcomeIntents {
    object GetCurrentUser : WelcomeIntents
}