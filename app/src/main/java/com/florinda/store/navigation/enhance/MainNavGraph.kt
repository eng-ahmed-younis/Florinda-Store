package com.florinda.store.navigation.enhance

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.board.on_boarding.OnBoardingScreen
import com.florinda.store.ui.screens.board.on_boarding.OnBoardingViewModel
import com.florinda.store.ui.screens.board.on_boarding.SplashScreen
import com.florinda.store.ui.screens.board.welcome.WelcomeScreen
import com.florinda.store.ui.screens.board.welcome.WelcomeViewModel
import com.florinda.store.ui.screens.main.home.HomeScreen
import com.florinda.store.ui.screens.main.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    rootNavBackStackEntry: NavBackStackEntry?
) {

    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ){



        composable(
            route = AppScreen.Main.Home.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable fadeOut(tween(700))
            },
        ){
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                navController,
                state = viewModel.state.collectAsState(),
                intentChannel = viewModel.intentChannel
            )
        }


    }

}