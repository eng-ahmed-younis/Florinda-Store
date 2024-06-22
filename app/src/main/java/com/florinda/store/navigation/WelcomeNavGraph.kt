package com.florinda.store.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.florinda.store.ui.screens.welcome.on_boarding.OnBoardingScreen
import com.florinda.store.ui.screens.welcome.on_boarding.OnBoardingViewModel
import com.florinda.store.ui.screens.welcome.splash.SplashScreen
import com.florinda.store.ui.screens.welcome.welcome.WelcomeScreen
import com.florinda.store.ui.screens.welcome.welcome.WelcomeViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.navigation

fun NavGraphBuilder.welcomeNavGraph() {

    navigation(
        route = AppScreen.WelcomeGraph.route,
        startDestination = AppScreen.WelcomeGraph.SplashScreen.route
    ) {

        /* SplashScreen */
        composable(
            AppScreen.WelcomeGraph.SplashScreen.route,
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
            AppScreen.WelcomeGraph.OnBoardingScreen.route,
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
            AppScreen.WelcomeGraph.WelcomeScreen.route,
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