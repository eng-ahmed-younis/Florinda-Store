package com.florinda.store.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.florinda.store.ui.screens.main.home.HomeScreen
import com.florinda.store.ui.screens.main.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.mainNavGraph() {

    navigation(
        route = AppScreen.MainGraph.route,
        startDestination = AppScreen.MainGraph.HomeScreen.route
    ){

        composable(
            route = AppScreen.MainGraph.HomeScreen.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            },
            exitTransition = {
                return@composable fadeOut(tween(700))
            },
        ){
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                state = viewModel.state.collectAsState(),
                intentChannel = viewModel.intentChannel
            )
        }
    }

}