package com.florinda.store.app_component

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight

@Composable
fun ScreenAppBar(
    screenTitle: String,
    onBackClicked: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart),
            onClick = {
                onBackClicked()
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBackIos,
                contentDescription = "back button",
                tint = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = screenTitle,
            color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ScreenAppBarPreview() {
    ScreenAppBar("Sing In") {}
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ScreenAppBarPreviewDark() {
    ScreenAppBar("Sing In") {}
}