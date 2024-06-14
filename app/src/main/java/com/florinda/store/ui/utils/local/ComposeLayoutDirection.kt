package com.florinda.store.ui.utils.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun ComposeLayoutDirection(
    direction: LayoutDirection ,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        content()
    }
}
