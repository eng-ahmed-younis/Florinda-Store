package com.florinda.data.repository

import com.florinda.data.data_source.remote.ApiService
import com.florinda.data.mappers.toCategoriesModel
import com.florinda.data.mappers.toOrdersModel
import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrdersModel
import com.florinda.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val apiService: ApiService
)  : OrderRepository {

    override suspend fun getOrders(): OrdersModel = apiService.getOrders().toOrdersModel()

    override suspend fun getCategories(): List<CategoryModel>  = apiService.getCategories().toCategoriesModel()

    override suspend fun getOrdersBYCategories(categoryId: Int): OrdersModel  = apiService.getOrdersByCategories(categoryId).toOrdersModel()

}