package com.florinda.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.florinda.store.component.StandardScaffold
import com.florinda.store.navigation.NavigationGraph
import com.florinda.store.navigation.Router
import com.florinda.store.ui.theme.FlorindaFoodsTheme
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlorindaFoodsTheme {
                FlorindaFoodsUIMain(
                    lifecycleScope
                )
            }
        }
    }
}

@Composable
fun FlorindaFoodsUIMain(
    lifecycleCoroutineScope: LifecycleCoroutineScope
) {
    val scope = rememberCoroutineScope()
    val connectivityObserver = get<ConnectivityObserver>()
    /* convert [Flow<ConnectivityObserver.Status>] to StateFlow that can
      be shared with multi collector
      Eagerly = meaning state flow start sharing value immediatly
      and stop when no collector active
      */

    val connectivityStatus = connectivityObserver.observe().stateIn(
        scope = lifecycleCoroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = ConnectivityObserver.State.Lost
    )


    FlorindaFoodsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            StandardScaffold(
                navController = navController,
                showBottomBar = navBackStackEntry?.destination?.route in listOf(
                    Router.HomeScreen.route,
                    Router.FavoriteScreen.route,
                    Router.CardScreen.route,
                    Router.ProfileScreen.route,
                ),
                modifier = Modifier.fillMaxSize(),
                onFabClick = {
                    navController.navigate(Router.SearchScreen.route)
                }
            ) {
                NavigationGraph(navController, connectivityStatus)
            }
        }

    }
}

