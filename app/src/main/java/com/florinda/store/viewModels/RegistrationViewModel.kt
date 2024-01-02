package com.florinda.store.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.model.Resource
import com.florinda.domain.use_case.auth_use_case.RegisterUseCase
import com.florinda.store.ui.screens.auth.register.RegisterIntents
import com.florinda.store.ui.screens.auth.register.RegisterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel constructor(
    private val registerUseCase: RegisterUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentChannel = Channel<RegisterIntents>(Channel.UNLIMITED)

    var state: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState())
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
                        is RegisterIntents.RegisterNewAccount -> {registerNewUser(it.email,it.password)}
                    }
                }
        }
    }


    private fun registerNewUser(email: String, password: String) = viewModelScope.launch {
        registerUseCase(email, password).collect { result ->
            when(result){
                is Resource.Loading ->{
                    state.update { it.copy(isLoading = true) }
                }
                is Resource.Success->{
                    state.update { it.copy(isLoading = false , data = result.data) }
                }
                is Resource.Error ->{
                    state.update { it.copy(error = result.message) }
                }
            }

        }
    }


}