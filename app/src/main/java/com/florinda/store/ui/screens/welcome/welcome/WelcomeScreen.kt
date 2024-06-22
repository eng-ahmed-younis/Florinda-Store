package com.florinda.store.ui.screens.welcome.welcome

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.florinda.store.navigation.AppScreen
import com.florinda.store.ui.screens.main.LocalLanguage
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.utils.currentDirection
import com.florinda.store.ui.utils.local.ComposeLayoutDirection
import kotlinx.coroutines.channels.Channel

@Composable
fun WelcomeScreen(
    state: State<WelcomeState>,
    intentChannel: Channel<WelcomeIntents>,
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val language = LocalLanguage.current

    LaunchedEffect(key1 = Unit) {
        intentChannel.send(WelcomeIntents.GetCurrentUser)
    }

    ComposeLayoutDirection(
        direction = context.currentDirection(languageItem = language)
    ) {

        WelcomeScreenContent(
            onBackClicked = {
                navController.popBackStack()
            },
            getOrderClicked = {
                navController.popBackStack()
                if (state.value.currentUser == null) {
                    navController.navigate(AppScreen.AuthGraph.route) {
                        popUpTo(AppScreen.WelcomeGraph.WelcomeScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(AppScreen.MainGraph.route) {
                        popUpTo(AppScreen.WelcomeGraph.WelcomeScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        )
    }


}


@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(
        state = remember {
            mutableStateOf(WelcomeState(false, null, null))
        },
        Channel { }
    )
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun WelcomeScreenDarkPreview() {
    WelcomeScreen(
        state = remember {
            mutableStateOf(WelcomeState(false, null, null))
        },
        Channel { }
    )
}
