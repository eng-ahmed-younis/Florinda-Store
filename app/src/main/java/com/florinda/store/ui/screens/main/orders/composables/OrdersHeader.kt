package com.florinda.store.ui.screens.main.orders.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.store.R
import com.florinda.store.ui.theme.colorItemsDark
import com.florinda.store.ui.theme.colorItemsLight

@Composable
fun OrdersHeader(
    searchButtonClick: () -> Unit,
    backClicked: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        IconButton(
            onClick = { backClicked() },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIos,
                contentDescription = "previous screen",
                tint = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark
            )
        }


        Text(
            text = stringResource(R.string.popular_items),
            color = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark,
            fontSize = 18.sp,
            )

        IconButton(
            onClick = { searchButtonClick() },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "search orders",
                tint = if (isSystemInDarkTheme()) colorItemsLight else colorItemsDark
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun OrdersHeaderPreview() {
    OrdersHeader({}, {})
}

