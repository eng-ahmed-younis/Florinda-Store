package com.florinda.store.app_component

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.florinda.store.ui.theme.colorBackgroundDark


@Composable
fun ComponentActivity.HandleSystemColors(isDarkMode: Boolean){
    DisposableEffect(isDarkMode) {
        this@HandleSystemColors.enableEdgeToEdge(
            statusBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    Color.TRANSPARENT,
                    Color.TRANSPARENT
                )
            } else {
                SystemBarStyle.dark(
                    Color.TRANSPARENT
                )
            },
            navigationBarStyle = if (!isDarkMode) {
                SystemBarStyle.light(
                    Color.WHITE,
                    Color.BLACK
                )
            } else {
                SystemBarStyle.dark(colorBackgroundDark.toArgb())
            }
        )
        onDispose { }
    }
}
