package com.florinda.store.ui.screens.board.welcome

sealed interface WelcomeIntents {
    object GetCurrentUser : WelcomeIntents
}