package com.florinda.store.ui.screens.auth.forget_password

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme(),
){
    Text(text = "ForgetPasswordScreen")
}


@Composable
@Preview(
    showBackground = true ,
    showSystemUi = true,
)
fun ForgetPasswordScreenPreviewLight(){
    ForgetPasswordScreen(
        rememberNavController()
    )
}


@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES ,
    showBackground = true ,
    showSystemUi = true,
)
fun ForgetPasswordScreenPreviewNight(){
ForgetPasswordScreen(
    rememberNavController()
)
}

