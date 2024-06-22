package com.florinda.store.ui.screens.main.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.model.Resource
import com.florinda.domain.use_case.GetOrdersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentOrdersChannel = Channel<OrdersIntents>(Channel.UNLIMITED)

    var state: MutableStateFlow<OrdersState> = MutableStateFlow(OrdersState())
        private set

    init {
        onEvent()
    }

    // act as process
    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentOrdersChannel.consumeAsFlow().collect {
                when (it) {
                    is OrdersIntents.GetOrders -> getOrders()
                }
            }
        }
    }


    private fun getOrders() {
        viewModelScope.launch(dispatcher) {
            getOrdersUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> state.update { it.copy(isLoading = true) }
                    is Resource.Success -> state.update {
                        it.copy(
                            isLoading = false,
                            data = result.data.orders
                        )
                    }

                    is Resource.Error -> state.update { it.copy(error = result.message) }
                }
            }
        }
    }


}