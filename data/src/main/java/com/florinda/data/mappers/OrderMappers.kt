package com.florinda.data.mappers

import com.florinda.data.dto.order.CategoryDto
import com.florinda.data.dto.order.OrderDto
import com.florinda.data.dto.order.OrdersDto
import com.florinda.domain.model.CategoryModel
import com.florinda.domain.model.OrderModel
import com.florinda.domain.model.OrdersModel


fun OrdersDto.toOrdersModel() : OrdersModel {
    return OrdersModel(orders = this.map { it.toOrderModel() })
}

fun List<CategoryDto>.toCategoriesModel() : List<CategoryModel> {
    return this.map { it.toCategoryModel() }
}


fun CategoryDto.toCategoryModel() : CategoryModel {
    return CategoryModel(
        id = id,
        image = image,
        name = name
    )
}


fun OrderDto.toOrderModel() : OrderModel {
    return OrderModel(
        category = category.toCategoryModel(),
        creationAt = creationAt,
        description = description,
        id = id,
        images = images,
        price = price,
        title = title,
        updatedAt = updatedAt
    )
}







