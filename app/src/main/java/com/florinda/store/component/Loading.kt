package com.florinda.store.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.florinda.store.R
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorWhite

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color =  colorWhite
        )
        Text(
            text = stringResource(id = R.string.loading),
            fontSize = 18.sp,
            color = colorWhite
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingScreenPreview() {
    LoadingScreen()
}