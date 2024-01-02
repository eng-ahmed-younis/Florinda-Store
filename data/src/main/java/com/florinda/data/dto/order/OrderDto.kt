package com.florinda.data.dto.order

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