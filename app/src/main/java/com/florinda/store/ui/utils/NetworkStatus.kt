package com.florinda.store.ui.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

object NetworkStatus {
    fun getConnectivityStatus(connectivityObserver: ConnectivityObserver , lifecycleCoroutineScope: CoroutineScope) : StateFlow<ConnectivityObserver.State> {
        return connectivityObserver.observe().stateIn(
            scope = lifecycleCoroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = ConnectivityObserver.State.Lost
        )
    }
}