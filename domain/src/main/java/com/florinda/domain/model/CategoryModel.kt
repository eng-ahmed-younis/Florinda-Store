package com.florinda.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val id: Int,
    val image: String,
    val name: String,
) : Parcelable