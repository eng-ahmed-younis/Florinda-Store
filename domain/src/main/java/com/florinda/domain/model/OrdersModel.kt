package com.florinda.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrdersModel (val orders : List<OrderModel>) : Parcelable
