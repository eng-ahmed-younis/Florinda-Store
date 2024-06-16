package com.florinda.store.navigation.enhance

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.florinda.store.ui.screens.auth.forget_password.ForgetPasswordScreen
import com.florinda.store.ui.screens.auth.login.LoginScreen
import com.florinda.store.ui.screens.auth.login.LoginViewModel
import com.florinda.store.ui.screens.auth.otp.OtpVerifyScreen
import com.florinda.store.ui.screens.auth.register.RegistrationScreen
import com.florinda.store.ui.screens.auth.register.RegistrationViewModel
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authNavGraph(
    connectivityStatus: StateFlow<ConnectivityObserver.State>
) {
    navigation(
        startDestination = AppScreen.Auth.Login.route,
        route = AppScreen.Main.route
    ){
        /* LoginScreen */
        composable(
            AppScreen.Auth.Login.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel = koinViewModel<LoginViewModel>()
            LoginScreen(
                state = viewModel.state.collectAsState(),
                intentChannel = viewModel.intentChannels,
                connectivityStatus = connectivityStatus
            )
        }



        /* Registration Screen */
        composable(
            AppScreen.Auth.Register.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel = koinViewModel<RegistrationViewModel>()
            RegistrationScreen(
                state = viewModel.state.collectAsState(),
                intentChannel = viewModel.intentChannel
            )
        }


        /* ForgotPasswordScreen */
        composable(
            AppScreen.Auth.ForgetPassword.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            ForgetPasswordScreen()
        }



        /* OTP Screen */
        composable(
            AppScreen.Auth.Otp.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            OtpVerifyScreen()
        }
    }
}