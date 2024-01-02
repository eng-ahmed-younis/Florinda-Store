package com.florinda.store.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    connectivityStatus: StateFlow<ConnectivityObserver.State>
) {

    NavHost(
        navController = navHostController,
        startDestination = Router.SplashScreen.route,
        modifier = Modifier.fillMaxWidth()
    ) {
        buildNavigationGraph(navController = navHostController , connectivityStatus)
    }

}






