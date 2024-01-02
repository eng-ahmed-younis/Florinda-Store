package com.florinda.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKeys {
    val ON_BOARDING_SEEN = booleanPreferencesKey("IS_BOARD_SEEN")
    val WELCOME_SEEN = booleanPreferencesKey("IS_WELCOME_SEEN")
}