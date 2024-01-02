package com.florinda.store.ui.screens.orders

import com.florinda.store.ui.screens.bottom.home.HomeIntents

sealed interface OrdersIntents{
    object GetOrders : OrdersIntents
}