package com.florinda.store.ui.screens.main

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.florinda.store.app_component.HandleSystemColors
import com.florinda.store.app_component.StandardScaffold
import com.florinda.store.navigation.NavigationGraph
import com.florinda.store.navigation.Router
import com.florinda.store.ui.screens.main.setting.SettingViewModel
import com.florinda.store.ui.theme.FlorindaFoodsTheme
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("can not find NavHostController")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This call requests that your app display behind the system UI
        enableEdgeToEdge()
        setContent {
            val isDarkMode = isSystemInDarkTheme()
            HandleSystemColors(isDarkMode)
            FlorindaFoodsTheme {
                FlorindaFoodsUIMain(lifecycleScope)
            }
        }
    }
}

@Composable
fun FlorindaFoodsUIMain(
    lifecycleCoroutineScope: LifecycleCoroutineScope,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val connectivityObserver = get<ConnectivityObserver>()


    /* convert [Flow<ConnectivityObserver.Status>] to StateFlow that can be shared with multi collector
      Eagerly = meaning state flow start sharing value immediately and stop when no collector active */

    val settingViewModel: SettingViewModel = koinViewModel<SettingViewModel>()
    val themeUserSetting by settingViewModel.themeUserSetting.collectAsState()

    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else mutableStateOf(true)
    }

    val connectivityStatus = connectivityObserver.observe().stateIn(
        scope = lifecycleCoroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = ConnectivityObserver.State.Lost
    )

    CompositionLocalProvider(LocalNavController provides rememberNavController()) {

        FlorindaFoodsTheme {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.navigationBars)
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
}

