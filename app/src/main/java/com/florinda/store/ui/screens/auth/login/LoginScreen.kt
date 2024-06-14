package com.florinda.store.ui.screens.auth.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.R
import com.florinda.store.component.ScreenAppBar
import com.florinda.store.component.auth_component.Email
import com.florinda.store.component.auth_component.Password
import com.florinda.store.component.auth_component.UserButton
import com.florinda.store.navigation.Router
import com.florinda.store.ui.theme.SpaceLarge
import com.florinda.store.ui.theme.SpaceSmall
import com.florinda.store.ui.theme.colorBlack
import com.florinda.store.ui.theme.colorBottomBarBackground
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.theme.colorWhite
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoginScreen(
    navController: NavController,
    state: State<LoginState>,
    intentChannel: Channel<LoginIntents>,
    connectivityStatus: StateFlow<ConnectivityObserver.State>?,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {

    var isEmailEmpty by rememberSaveable { mutableStateOf(false) }
    var isPasswordEmpty by rememberSaveable { mutableStateOf(false) }
    var userEmail by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (darkTheme) colorBottomBarBackground else colorPrimary)
            .verticalScroll(rememberScrollState())
    ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ScreenAppBar(
                screenTitle = "Log In",
                onBackClicked = { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.height(20.dp))   
            Image(
                painter = painterResource(id = R.drawable.login_logo),
                contentDescription = "login logo",
                modifier = Modifier
                    .size(230.dp)
                    .padding(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Email(
                darkTheme = darkTheme,
                showError = isEmailEmpty
            ) {
                userEmail = it
            }

            Spacer(modifier = Modifier.height(SpaceSmall))
            Password(
                darkTheme,
                showError = isPasswordEmpty
            ) {
                userPassword = it
            }
            
            Spacer(modifier = Modifier.height(SpaceLarge))
            UserButton(
                darkTheme = darkTheme,
                title = stringResource(id = R.string.login_text),
                backgroundColor = if (darkTheme) colorPrimary else colorBlack
            ) {
                verifyUserEmailAndPassword(
                    email = userEmail,
                    password = userPassword,
                    emailState = { isEmailEmpty = it },
                    passwordState = { isPasswordEmpty = it }
                ).let {
                    if (it && connectivityStatus?.value == ConnectivityObserver.State.Available) {
                        Timber.i(connectivityStatus?.value?.name.toString())
                        scope.launch {
                            intentChannel.send(LoginIntents.LoginUser(userEmail, userPassword))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(SpaceSmall))
          /*  UserButton(
                darkTheme = darkTheme,
                title = stringResource(id = R.string.create_new_account),
                backgroundColor = if (darkTheme) colorPrimary else colorBlack
            ) {
                navController.popBackStack()
                navController.navigate(Router.RegistrationScreen.route)
            }*/



            Spacer(modifier = Modifier.height(SpaceLarge))
            ForgotPassword {
                navController.popBackStack()
                navController.navigate(Router.ForgotPasswordScreen.route)
            }
        }
    }
}


@Composable
fun ForgotPassword(onclickForget: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { onclickForget() }) {
            Text(
                text = stringResource(id = R.string.forget_password),
                color = colorWhite,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}


fun verifyUserEmailAndPassword(
    email: String,
    password: String,
    emailState: (Boolean) -> Unit,
    passwordState: (Boolean) -> Unit
): Boolean {
    if (email.isEmpty()) {
        emailState(true)
    }
    if (password.isEmpty()) {
        passwordState(true)
    }
    return email.isNotEmpty() && password.isNotEmpty()
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen(
        rememberNavController(),
        state = remember {
            mutableStateOf(
                LoginState(
                    isLoading = false, error = null, data = null
                )
            )
        },
        Channel { },
        null
    )
}


@Composable
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun LoginScreenPreviewDark() {
    LoginScreen(
        rememberNavController(),
        state = remember {
            mutableStateOf(
                LoginState(
                    isLoading = false, error = null, data = null
                )
            )
        },
        Channel { },
        null
    )
}



