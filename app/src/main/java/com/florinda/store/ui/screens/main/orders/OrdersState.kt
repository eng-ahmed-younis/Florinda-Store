package com.florinda.store.ui.screens.main.orders

import com.florinda.domain.model.OrderModel
import com.florinda.domain.model.OrdersModel

data class OrdersState (
    val isLoading : Boolean = false,
    val data : List<OrderModel>  = emptyList(),
    val error : String? = null
)