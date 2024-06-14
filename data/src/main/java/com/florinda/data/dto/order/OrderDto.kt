package com.florinda.data.dto.order

typealias OrdersDto  = ArrayList<OrderDto>

data class OrderDto(
    val category: CategoryDto,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)