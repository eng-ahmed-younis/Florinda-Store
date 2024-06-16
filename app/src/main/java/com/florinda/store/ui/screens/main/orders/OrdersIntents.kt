package com.florinda.store.ui.screens.main.orders

import com.florinda.store.ui.screens.main.home.HomeIntents

sealed interface OrdersIntents{
    object GetOrders : OrdersIntents
}