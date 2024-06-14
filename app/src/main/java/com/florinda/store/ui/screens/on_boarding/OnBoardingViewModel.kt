package com.florinda.store.ui.screens.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.repository.IPreferenceDataStoreAPI
import com.florinda.utils.DataStoreKeys.ON_BOARDING_SEEN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class OnBoardingViewModel constructor(
    private val dataStore: IPreferenceDataStoreAPI,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentChannel = Channel<OnBoardIntents>(Channel.UNLIMITED)


    var state = MutableStateFlow(OnBoardingState())
        private set


    init {
        onEvent()
        isOnBoardingSeen()
    }


    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    OnBoardIntents.OnBoardingFinish -> {
                        state.update { it.copy(onBoardSeen = true) }
                        dataStore.putPreference(ON_BOARDING_SEEN, true)
                    }
                }
            }
        }
    }


    private fun isOnBoardingSeen(){
        viewModelScope.launch (dispatcher) {
            dataStore.getPreferencs(ON_BOARDING_SEEN, false).collect { seen ->
                state.update { it.copy(onBoardSeen = seen) }
            }
        }
    }

}