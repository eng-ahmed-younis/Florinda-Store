package com.florinda.store.ui.screens.auth.register

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.R
import com.florinda.store.app_component.ScreenAppBar
import com.florinda.store.app_component.auth_component.Email
import com.florinda.store.app_component.auth_component.Password
import com.florinda.store.app_component.auth_component.UserButton
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.theme.SpaceMedium
import com.florinda.store.ui.theme.SpaceSmall
import com.florinda.store.ui.theme.SpaceXLarge
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorRedLite
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun RegistrationScreen(
    state: State<RegisterState>,
    intentChannel: Channel<RegisterIntents>,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    val scope = rememberCoroutineScope()
    val navController = LocalNavController.current


    LaunchedEffect(key1 = state.value.data) {
        if (state.value.data != null) {
            Timber.i(state.value?.data?.email.toString())
            navController.popBackStack()
            navController.navigate(Router.HomeScreen.route)
        }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState())
    ) {
        ScreenAppBar(
            screenTitle = stringResource(R.string.sing_up),
            onBackClicked = { navController.popBackStack() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Email(darkTheme = darkTheme, onEmailChanged = { email = it })
            Spacer(modifier = Modifier.height(SpaceMedium))
            Password(darkTheme = darkTheme, onPasswordChanged = { password = it })
            Spacer(modifier = Modifier.height(SpaceXLarge))
            UserButton(
                darkTheme = darkTheme,
                title = stringResource(id = R.string.create_new_account),
                backgroundColor = if (darkTheme) colorPrimary else colorBlack
            ) {
                scope.launch {
                    intentChannel.send(RegisterIntents.RegisterNewAccount(email, password))
                }
            }

            Spacer(modifier = Modifier.height(SpaceSmall))
            UserButton(
                darkTheme = darkTheme,
                title = stringResource(id = R.string.login_text),
                backgroundColor = if (darkTheme) colorPrimary else colorRedLite
            ) {
                navController.popBackStack()
                navController.navigate(Router.LoginScreen.route)
            }
            Spacer(modifier = Modifier.height(SpaceXLarge))
        }

    }
}


@Composable
@Preview
fun RegistrationScreenPreview() {
    RegistrationScreen(
        state = remember {
            mutableStateOf(
                RegisterState(
                    false,
                    null,
                    null
                )
            )
        },
        Channel { }
    )
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun RegistrationScreenDarkPreview() {
    RegistrationScreen(
        state = remember {
            mutableStateOf(
                RegisterState(
                    false,
                    null,
                    null
                )
            )
        },
        Channel { }
    )
}
