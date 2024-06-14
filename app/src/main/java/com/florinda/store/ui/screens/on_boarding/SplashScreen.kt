package com.florinda.store.ui.screens.on_boarding

import android.content.res.Configuration
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.R
import com.florinda.store.navigation.Router
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    state : State<OnBoardingState>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {


    val scale = remember { Animatable(initialValue = 0f) }
    val transition = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(key1 = true) {

        scale.animateTo(
            targetValue = 0.3f,
            // duration of animation
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        if (state.value.onBoardSeen) {
            navigateToWelcome(navController)
        } else {
            navigateToOnBoarding(navController)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
        //    .safeDrawingPadding()
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "splash screen",
            modifier = Modifier
                .size(200.dp)

        )
    }

}


fun navigateToOnBoarding(navController: NavController){
    navController.navigate(Router.OnBoardingScreen.route) {
        popUpTo(Router.SplashScreen.route) {
            inclusive = true
        }
    }
}

fun navigateToWelcome(navController: NavController){
    navController.navigate(Router.WelcomeScreen.route) {
        popUpTo(Router.SplashScreen.route) {
            inclusive = true
        }
    }
}





@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        rememberNavController(),
        remember {
            mutableStateOf(OnBoardingState(false))
        }
    )
}


@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreviewDark() {
    SplashScreen(
        rememberNavController(),
        remember {
            mutableStateOf(OnBoardingState(false))
        }
    )
}