package com.florinda.store.ui.screens.search

import androidx.compose.runtime.mutableStateOf
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

class SearchViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    val intentSearchChannel = Channel<SearchIntents>(Channel.UNLIMITED)
    var state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
        private set

    private var firstSearch = mutableStateOf(false)


    init {
        onEvent()
    }

    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentSearchChannel.consumeAsFlow().collect {
                when (it) {
                    is SearchIntents.GetOrders -> {
                        getOrders()
                    }

                    is SearchIntents.SearchByName -> searchOrdersByName(it.queryName)
                }
            }
        }
    }

    private fun searchOrdersByName(query: String) {
        if (query.isNotEmpty()) {
            firstSearch.value = true
            viewModelScope.launch(dispatcher) {
                getOrdersUseCase().collect {
                    state.update {
                        it.copy(
                            searchOrdersResult = state.value.orders.filter { order ->
                                order.title.contains(query, ignoreCase = true)
                            }
                        )
                    }
                }
            }
        } else {
            state.update {
                it.copy(searchOrdersResult = it.orders)
            }
        }
    }


    private fun getOrders() {
        viewModelScope.launch(dispatcher) {
            getOrdersUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        if (!firstSearch.value) {
                            state.update {
                                it.copy(
                                    orders = result.data.orders,
                                    searchOrdersResult = result.data.orders
                                )
                            }

                        } else {
                            state.update {
                                it.copy(orders = result.data.orders)
                            }
                        }

                    }

                    is Resource.Error -> {
                        state.update {
                            it.copy(searchOrdersResult = emptyList())
                        }
                    }
                }
            }
        }
    }

}