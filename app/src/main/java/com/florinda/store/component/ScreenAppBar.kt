package com.florinda.store.component

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight
import com.florinda.store.ui.theme.colorPrimary

@Composable
fun ScreenAppBar(
    modifier: Modifier = Modifier,
    screenTitle: String,
    onBackClicked: () -> Unit,
) {

    Row(
        modifier = modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(modifier = Modifier.weight(0.7f)) {
            IconButton(
                onClick = {
                    onBackClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIos,
                    contentDescription = "back button",
                    tint = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark
                    )
            }
        }
        Box(modifier = Modifier.weight(1.0f)) { 
            Text(
                text = screenTitle,
                color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }
}

@Composable
fun ScreenTitle(
    title: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = title,
        fontSize = 26.sp,
        color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,

        modifier = modifier

    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ScreenAppBarPreview() {
    ScreenAppBar(modifier = Modifier, "Sing In", {})
}

@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ScreenAppBarPreviewDark() {
    ScreenAppBar(modifier = Modifier, "Sing In", {})

}