package com.florinda.store.ui.screens.main.search

import com.florinda.domain.model.OrderModel

data class SearchState(
    val isLoading : Boolean = false,
    var searchOrdersResult : List<OrderModel>  = emptyList(),
    var orders : List<OrderModel>  = emptyList(),
    val error : String? = null
)