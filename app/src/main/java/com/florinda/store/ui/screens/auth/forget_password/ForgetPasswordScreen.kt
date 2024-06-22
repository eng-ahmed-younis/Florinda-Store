package com.florinda.store.ui.screens.auth.forget_password

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.screens.main.LocalTheme


@Composable
fun ForgetPasswordScreen(
){
    val navController = LocalNavController.current
    val darkTheme = LocalTheme.current.isDark

    Text(text = "ForgetPasswordScreen")
}


@Composable
@Preview(
    showBackground = true ,
    showSystemUi = true,
)
fun ForgetPasswordScreenPreviewLight(){
    ForgetPasswordScreen(

    )
}


@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES ,
    showBackground = true ,
    showSystemUi = true,
)
fun ForgetPasswordScreenPreviewNight(){
ForgetPasswordScreen()
}

