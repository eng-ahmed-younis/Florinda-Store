package com.florinda.store.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorWhite

@Composable
fun TopAppBarHome(
    notificationIconOnClick: () -> Unit,
    dashboardIconOnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DashboardIcon { dashboardIconOnClick() }
        NotificationIcon { notificationIconOnClick() }


    }
}


@Composable
fun NotificationIcon(notificationOnClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape)
            .background(colorWhite)
    ) {
        IconButton(onClick = { notificationOnClick() }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                tint = colorBlack
            )

        }
    }
}


@Composable
fun DashboardIcon(dashboardOnClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(shape = CircleShape)
            .background(colorWhite)
    ) {
        IconButton(onClick = { dashboardOnClick() }) {
            Icon(
                imageVector = Icons.Outlined.Dashboard,
                contentDescription = "",
                tint = colorBlack
            )

        }
    }
}


@Composable
@Preview
fun TopAppBarHomeScreenPreview() {
    TopAppBarHome({}, {})
}
