package com.florinda.store.navigation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.florinda.store.app_component.StandardScaffold
import com.florinda.store.ui.screens.main.LocalNavController
import com.florinda.store.ui.screens.main.setting.SettingViewModel
import com.florinda.store.ui.utils.ConnectivityObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel


@Composable
fun RootNavHost(isAuthenticated: Boolean, lifecycleCoroutineScope: LifecycleCoroutineScope) {

    val context = LocalContext.current
    val rootNavHostController = LocalNavController.current
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
                AppScreen.MainGraph.HomeScreen.route,
                AppScreen.MainGraph.FavoritesScreen.route,
                AppScreen.MainGraph.CardScreen.route,
                AppScreen.MainGraph.ProfileScreen.route,
            ),
            modifier = Modifier.fillMaxSize(),
            onFabClick = {
                navController.navigate(AppScreen.MainGraph.SearchScreen.route)
            }
        ) {
            NavHost(
                navController = rootNavHostController,
                //  startDestination = if (isAuthenticated) AppScreen.Main.route else AppScreen.Auth.route,
                startDestination = AppScreen.WelcomeGraph.route,
                enterTransition = {
                    // you can change whatever you want transition
                    EnterTransition.None
                },
                exitTransition = {
                    // you can change whatever you want transition
                    ExitTransition.None
                }
            ) {
                welcomeNavGraph()
                authNavGraph()
                mainNavGraph()
            }
        }
    }
}
