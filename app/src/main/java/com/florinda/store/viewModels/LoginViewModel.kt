package com.florinda.store.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.model.Resource
import com.florinda.domain.use_case.auth_use_case.LoginUseCase
import com.florinda.store.ui.screens.auth.login.LoginIntents
import com.florinda.store.ui.screens.auth.login.LoginState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel constructor(
    private val loginUseCase: LoginUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentChannels = Channel<LoginIntents>(Channel.UNLIMITED)

    var state = MutableStateFlow(LoginState())
        private set


    init {
        onEvent()
    }


    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentChannels.consumeAsFlow().collect {
                when (it) {
                    is LoginIntents.LoginUser -> {
                        loginUser(it.email, it.password)
                    }
                }
            }
        }
    }


    private fun loginUser(email: String, password: String) = viewModelScope.launch {
        loginUseCase(email, password).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    state.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    state.update { it.copy(isLoading = false, data = it.data) }
                }

                is Resource.Error -> {
                    state.update { it.copy(error = it.error) }
                }
            }
            Timber.i("called")
        }
    }


}