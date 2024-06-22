package com.florinda.store.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.florinda.store.app_component.HandleSystemColors
import com.florinda.store.navigation.RootNavHost
import com.florinda.store.ui.screens.main.setting.SettingViewModel
import com.florinda.store.ui.theme.AppTheme
import com.florinda.store.ui.theme.DarkTheme
import com.florinda.store.ui.theme.FlorindaFoodsTheme
import com.florinda.store.ui.utils.ConnectivityObserver
import com.florinda.store.ui.utils.NetworkStatus
import com.florinda.store.ui.utils.local.LanguageItem
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("can not find NavHostController")
}
val LocalNetworkStatus = staticCompositionLocalOf<StateFlow<ConnectivityObserver.State>> {
    error("can not find Network Status")
}
val LocalTheme = staticCompositionLocalOf<DarkTheme> {
    error("No local theme found")
}
val LocalLanguage = staticCompositionLocalOf<LanguageItem> {
    error("No Language found")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This call requests that your app display behind the system UI
        enableEdgeToEdge()
        setContent {
            HandleSystemColors(isSystemInDarkTheme())
            FlorindaFoodsTheme {
                MainScreenContent(lifecycleScope)
            }
        }
    }
}

@Composable
fun MainScreenContent(
    lifecycleCoroutineScope: LifecycleCoroutineScope,
) {
    val connectivityObserver = get<ConnectivityObserver>()
    val settingViewModel: SettingViewModel = koinViewModel()
    val themeUserSetting by settingViewModel.themeUserSetting.collectAsState()
    val darkTheme = when (themeUserSetting) {
        AppTheme.MODE_AUTO -> {
            DarkTheme(isSystemInDarkTheme())
        }

        AppTheme.MODE_NIGHT -> {
            DarkTheme(true)
        }

        AppTheme.MODE_DAY -> {
            DarkTheme(false)
        }
    }

    CompositionLocalProvider(
        LocalNavController provides rememberNavController(),
        LocalNetworkStatus provides NetworkStatus.getConnectivityStatus(
            connectivityObserver,
            lifecycleCoroutineScope
        ),
        LocalTheme provides darkTheme,
        LocalLanguage provides settingViewModel.getCurrentLanguage()
    ) {
        RootNavHost(isAuthenticated = true, lifecycleCoroutineScope)
    }
}

