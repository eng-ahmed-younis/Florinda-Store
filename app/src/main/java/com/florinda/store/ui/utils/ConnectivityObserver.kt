package com.florinda.store.ui.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe () : Flow<State>

    enum class State{
        Available , UnAvailable , Losing , Lost
    }
}