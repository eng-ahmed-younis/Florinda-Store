package com.florinda.store

import android.R.attr.name
import android.R.id
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.florinda.store.component.StandardScaffold
import com.florinda.store.navigation.NavigationGraph
import com.florinda.store.navigation.Router
import com.florinda.store.ui.theme.AppColor
import com.florinda.store.ui.theme.FlorindaFoodsTheme
import com.florinda.store.ui.theme.colorBackgroundDark
import com.florinda.store.ui.theme.colorBackgroundLight
import com.florinda.store.ui.theme.colorPrimary
import com.florinda.store.ui.utils.ConnectivityObserver
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.get

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("can not find NavHostController")
}

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This call requests that your app display behind the system UI
        enableEdgeToEdge()
       // WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navigationBarLight = Color.GREEN
            val navigationBarDark = Color.BLUE
            val isDarkMode = isSystemInDarkTheme()
            val context = LocalContext.current as ComponentActivity

            DisposableEffect(isDarkMode) {
                context.enableEdgeToEdge(
                    statusBarStyle = if (!isDarkMode) {
                        SystemBarStyle.light(
                            Color.TRANSPARENT,
                            Color.TRANSPARENT
                          //  colorBackgroundDark.toArgb()
                        )
                    } else {
                        SystemBarStyle.dark(
                            Color.TRANSPARENT
                        )
                    },
                    navigationBarStyle = if(!isDarkMode){
                        SystemBarStyle.light(
                            navigationBarLight,
                            navigationBarDark
                        )
                    } else {
                        SystemBarStyle.dark(navigationBarDark)
                    }
                )

                onDispose { }
            }

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

