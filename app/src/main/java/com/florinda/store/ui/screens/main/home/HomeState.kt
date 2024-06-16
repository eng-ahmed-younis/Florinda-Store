package com.florinda.store.ui.screens.main.home

import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrdersModel


data class HomeState (
    val  loading : Boolean = false,
    val  error : String = "",
    val  orders :  OrdersModel? = null,
    val  categories : List<CategoryModel>  = emptyList(),
    val  ordersByCategories : OrdersModel? = null
)





