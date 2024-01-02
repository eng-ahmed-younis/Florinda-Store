package com.florinda.domain.repository

import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrderModel
import com.florinda.domain.model.OrdersModel

interface OrderRepository {
    suspend fun getOrders() : OrdersModel
    suspend fun getCategories() : List<CategoryModel>
    suspend fun getOrdersBYCategories(categoryId: Int) : OrdersModel
}