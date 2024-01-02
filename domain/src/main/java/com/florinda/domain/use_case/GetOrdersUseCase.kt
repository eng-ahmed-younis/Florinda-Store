package com.florinda.domain.use_case

import android.util.Log
import com.florinda.domain.model.Resource
import com.florinda.domain.model.OrdersModel
import com.florinda.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetOrdersUseCase(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(): Flow<Resource<OrdersModel>> = flow {
        emit(Resource.Loading)
        val orders = orderRepository.getOrders()
        Log.i("GetOrdersUseCase", orders.orders.size.toString())
        emit(Resource.Success(orders))
    }.catch {
        emit(Resource.Error(it.message ?: "un known error"))
    }.flowOn(Dispatchers.IO)
}