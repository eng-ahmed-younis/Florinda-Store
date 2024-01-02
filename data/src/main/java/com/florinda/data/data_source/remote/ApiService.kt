package com.florinda.data.data_source.remote

import com.florinda.data.dto.order.CategoryDto
import com.florinda.data.dto.order.OrdersDto
import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrderModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getOrders(): OrdersDto

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("products/")
   suspend fun getOrdersByCategories(
        @Query("categoryId") categoryId: Int
    ): OrdersDto



}