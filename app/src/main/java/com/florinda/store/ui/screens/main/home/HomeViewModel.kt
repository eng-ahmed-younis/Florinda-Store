package com.florinda.store.ui.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florinda.domain.use_case.GetCategoriesUseCase
import com.florinda.domain.use_case.GetOrdersByCategoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getOrdersByCategoriesUseCase: GetOrdersByCategoriesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val intentChannel = Channel<HomeIntents>(Channel.UNLIMITED)

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    init {
        onEvent()
    }

    // act as process
    private fun onEvent() {
        viewModelScope.launch(dispatcher) {
            intentChannel.consumeAsFlow().distinctUntilChanged()
                .collect {
                    when (it) {
                        is HomeIntents.GetCategories -> getCategories()
                        is HomeIntents.GetOrdersByCategory -> getOrdersByCategories(it.categoryId)
                    }
                }
        }
    }


    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                when (result) {
                    is com.florinda.domain.model.Resource.Loading -> _state.update { it.copy(loading = true) }
                    is com.florinda.domain.model.Resource.Success -> _state.update {
                        it.copy(
                            loading = false,
                            categories = result.data
                        )
                    }

                    is com.florinda.domain.model.Resource.Error -> _state.update { it.copy(error = result.message) }
                }
            }
        }
    }

    private fun getOrdersByCategories(id: Int) {
        viewModelScope.launch(dispatcher) {
            getOrdersByCategoriesUseCase(id).collect { result ->
                when (result) {
                    is com.florinda.domain.model.Resource.Loading -> _state.update {
                        it.copy(loading = true)
                    }

                    is com.florinda.domain.model.Resource.Success -> _state.update {
                        it.copy(
                            loading = false,
                            ordersByCategories = result.data
                        )
                    }

                    is com.florinda.domain.model.Resource.Error -> _state.update { it.copy(error = result.message) }
                }
            }
        }
    }


}