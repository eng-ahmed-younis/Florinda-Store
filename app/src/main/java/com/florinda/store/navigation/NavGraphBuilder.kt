package com.florinda.store.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.florinda.store.ui.screens.on_boarding.SplashScreen
import com.florinda.store.ui.screens.welcome.WelcomeScreen
import com.florinda.store.ui.screens.auth.forget_password.ForgetPasswordScreen
import com.florinda.store.ui.screens.auth.register.RegistrationScreen
import com.florinda.store.ui.screens.auth.login.LoginScreen
import com.florinda.store.ui.screens.bottom.favourite.FavouriteScreen
import com.florinda.store.ui.screens.bottom.home.HomeScreen
import com.florinda.store.ui.screens.bottom.card.CardScreen
import com.florinda.store.viewModels.HomeViewModel
import com.florinda.store.ui.screens.bottom.profile.ProfileScreen
import com.florinda.store.viewModels.LoginViewModel
import com.florinda.store.ui.screens.orders.OrdersScreen
import com.florinda.store.ui.screens.search.SearchScreen
import com.florinda.store.viewModels.OrdersViewModel
import com.florinda.store.ui.screens.on_boarding.OnBoardingScreen
import com.florinda.store.viewModels.WelcomeViewModel
import com.florinda.store.viewModels.OnBoardingViewModel
import com.florinda.store.ui.utils.ConnectivityObserver
import com.florinda.store.viewModels.RegistrationViewModel
import com.florinda.store.viewModels.SearchViewModel
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.buildNavigationGraph(
    navController: NavController,
    connectivityStatus: StateFlow<ConnectivityObserver.State>
) {

    /* SplashScreen */
    composable(Router.SplashScreen.route,
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
            navController = navController,
            state = viewModel.state.collectAsState()
        )
    }



    /* OnBoarding Screen */
    composable(Router.OnBoardingScreen.route,
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
            navController = navController,
            intentChannel = viewModel.intentChannel
        )
    }



    /* WelcomeScreen */
    composable(Router.WelcomeScreen.route,
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
            navController = navController,
            state = viewModel.state.collectAsState(),
            intentChannel = viewModel.intentChannel
        )
    }



    /* LoginScreen */
    composable(Router.LoginScreen.route,
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
        val viewModel = koinViewModel<LoginViewModel>()
        LoginScreen(
            navController = navController,
            state = viewModel.state.collectAsState(),
            intentChannel = viewModel.intentChannels,
            connectivityStatus = connectivityStatus
        )
    }



    /* CreateAccountScreen */
    composable(
        Router.RegistrationScreen.route,
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
        val viewModel = koinViewModel<RegistrationViewModel>()
        RegistrationScreen(
            navController  = navController,
            state = viewModel.state.collectAsState(),
            intentChannel = viewModel.intentChannel
        )
    }



    /* HomeScreen */
    composable(Router.HomeScreen.route
    ) {
        val viewModel = koinViewModel<HomeViewModel>()
        HomeScreen(
            navController,
            state = viewModel.state.collectAsState(),
            intentChannel = viewModel.intentChannel
        )
    }


    /* OrdersScreen */
    composable(Router.OrdersScreen.route){
        val viewModel = koinViewModel<OrdersViewModel>()
        OrdersScreen(
            navController  = navController,
            state = viewModel.state.collectAsState(),
            intentOrdersChannel = viewModel.intentOrdersChannel
        )
    }



    /* ForgotPasswordScreen */
    composable(Router.ForgotPasswordScreen.route,
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
        ForgetPasswordScreen(
            navController = navController
        )
    }


    /* FavoriteScreen */
    composable(Router.FavoriteScreen.route
    ) {
        FavouriteScreen()
    }



    composable(Router.CardScreen.route) {
        CardScreen()
    }

    composable(Router.ProfileScreen.route) {
        ProfileScreen()
    }


    composable( Router.SearchScreen.route,
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
        val viewModel = koinViewModel<SearchViewModel>()
        SearchScreen(
            navController  = navController,
            state = viewModel.state.collectAsState(),
            intentSearchChannel = viewModel.intentSearchChannel
        )
    }



}





