package com.florinda.store.navigation.enhance

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.board.on_boarding.OnBoardingScreen
import com.florinda.store.ui.screens.board.on_boarding.OnBoardingViewModel
import com.florinda.store.ui.screens.board.on_boarding.SplashScreen
import com.florinda.store.ui.screens.board.welcome.WelcomeScreen
import com.florinda.store.ui.screens.board.welcome.WelcomeViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.navigation

fun NavGraphBuilder.welcomeNavGraph() {

    navigation(
        startDestination = AppScreen.Welcome.Splash.route,
        route = AppScreen.Welcome.route
    ) {

        /* SplashScreen */
        composable(
            AppScreen.Welcome.Splash.route,
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
            val viewModel = koinViewModel<OnBoardingViewModel>()
            SplashScreen(
                state = viewModel.state.collectAsState()
            )
        }



        /* OnBoarding Screen */
        composable(
            Router.OnBoardingScreen.route,
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
            val viewModel = koinViewModel<OnBoardingViewModel>()
            OnBoardingScreen(
                intentChannel = viewModel.intentChannel
            )
        }



        /* WelcomeScreen */
        composable(
            Router.WelcomeScreen.route,
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
            val viewModel = koinViewModel<WelcomeViewModel>()
            WelcomeScreen(
                state = viewModel.state.collectAsState(),
                intentChannel = viewModel.intentChannel
            )
        }

    }
}