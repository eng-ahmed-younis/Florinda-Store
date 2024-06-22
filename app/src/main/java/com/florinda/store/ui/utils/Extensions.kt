package com.florinda.store.ui.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import com.florinda.store.ui.utils.local.LanguageItem


fun String.hexColor() : Color{
    // parse rgb color like 0xFFFFE8E8
    val color = android.graphics.Color.parseColor(this)
    return Color(color)
}


fun Context.currentDirection(languageItem: LanguageItem) : LayoutDirection {
    return if (languageItem.value == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr
}