package com.florinda.store.ui.utils

import androidx.compose.ui.graphics.Color


fun String.hexColor() : Color{
    // parse rgb color like 0xFFFFE8E8
    val color = android.graphics.Color.parseColor(this)
    return Color(color)
}