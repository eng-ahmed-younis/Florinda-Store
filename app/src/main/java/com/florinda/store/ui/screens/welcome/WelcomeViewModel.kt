package com.florinda.store.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.model.Resource
import com.florinda.domain.use_case.auth_use_case.GetCurrentUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WelcomeViewModel constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentChannel = Channel<WelcomeIntents>(Channel.UNLIMITED)

    var state = MutableStateFlow(WelcomeState())
        private set


    init {
        onEvent()
    }


    // act as process
    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentChannel.consumeAsFlow().distinctUntilChanged()
                .collect {
                    when (it) {
                        is WelcomeIntents.GetCurrentUser -> {
                            getCurrentUser()
                        }
                    }
                }
        }
    }


    private fun getCurrentUser() = viewModelScope.launch {
        getCurrentUserUseCase().collect { result ->
            when (result) {
                is Resource.Loading -> state.update { it.copy(isLoading = true) }
                is Resource.Success -> state.update {
                    it.copy(
                        isLoading = false,
                        currentUser = result.data
                    )
                }
                is Resource.Error -> state.update { it.copy(error = result.message) }
            }

        }
    }


}