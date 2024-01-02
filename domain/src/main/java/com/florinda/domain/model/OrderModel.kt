package com.florinda.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class OrderModel(
    val category: CategoryModel,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
): Parcelable
