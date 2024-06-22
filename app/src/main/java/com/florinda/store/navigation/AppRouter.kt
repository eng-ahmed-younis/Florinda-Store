package com.florinda.store.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.florinda.store.R

private object Routes {

    // authentication
    const val AUTH_GRAPH = "auth_graph"
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val OTP_SCREEN = "otp"
    const val FORGET_PASSWORD_SCREEN = "forget_password"

    // Main
    const val MAIN_GRAPH = "main_graph"
    const val HOME_SCREEN = "home"
    const val FAVORITE_SCREEN = "favorite"
    const val SEARCH_SCREEN = "search"
    const val CARD_SCREEN = "card"
    const val ORDER_SCREEN = "order"
    const val PROFILE_SCREEN = "profile"
    const val NOTIFICATION_SCREEN = "notification"

    // Welcome
    const val WELCOME_GRAPH = "welcome_graph"
    const val SPLASH_SCREEN = "splash"
    const val ON_BOARD_SCREEN = "on_board"
    const val WELCOME_SCREEN = "welcome"

    // details
    const val PRODUCT_DETAIL = "productDetail/{${ArgParams.PRODUCT_ID}}"
    const val RECIPE_DETAIL = "recipeDetail/{${ArgParams.RECIPE_ID}}"

}

private object ArgParams {
    const val PRODUCT_ID = "productId"
    const val RECIPE_ID = "recipeId"
    fun toPath(param: String) = "{${param}}"
}

sealed class AppScreen(val route: String) {
    data object AuthGraph : AppScreen(Routes.AUTH_GRAPH) {
        data object LoginScreen : AppScreen(Routes.LOGIN_SCREEN)
        data object RegisterScreen : AppScreen(Routes.REGISTER_SCREEN)
        data object OtpScreen : AppScreen(Routes.OTP_SCREEN)
        data object ForgetPasswordScreen : AppScreen(Routes.FORGET_PASSWORD_SCREEN)
    }

    data object WelcomeGraph : AppScreen(Routes.WELCOME_GRAPH) {
        data object SplashScreen : AppScreen(Routes.SPLASH_SCREEN)
        data object OnBoardingScreen : AppScreen(Routes.ON_BOARD_SCREEN)
        data object WelcomeScreen : AppScreen(Routes.WELCOME_SCREEN)
    }


    data object MainGraph : TopLevelDestination(Routes.MAIN_GRAPH) {

        data object HomeScreen : TopLevelDestination(
            route = Routes.HOME_SCREEN,
            title = R.string.home,
        )

        data object NotificationScreen : TopLevelDestination(
            route = Routes.NOTIFICATION_SCREEN,
            title = R.string.notification,
        )

        data object ProfileScreen : TopLevelDestination(
            route = Routes.PROFILE_SCREEN,
            title = R.string.profile
        )

        data object SearchScreen : TopLevelDestination(
            route = Routes.SEARCH_SCREEN,
            title = R.string.search,
        )

        data object FavoritesScreen : TopLevelDestination(
            route = Routes.FAVORITE_SCREEN,
            title = R.string.favorite
        )

        data object CardScreen : TopLevelDestination(
            route = Routes.CARD_SCREEN,
            title = R.string.card
        )

        data object OrdersScreen : TopLevelDestination(
            route = Routes.ORDER_SCREEN,
            title = R.string.card
        )


        data object ProductDetailScreen : TopLevelDestination(
            route = Routes.PRODUCT_DETAIL,
            navArguments = listOf(navArgument(ArgParams.PRODUCT_ID) {
                type = NavType.Companion.StringType
            })
        ) {
            fun createRoute(productId: String) =
                Routes.PRODUCT_DETAIL
                    .replace(
                        ArgParams.toPath(ArgParams.PRODUCT_ID), productId
                    )

            fun isProductDetailRoute(route: String): Boolean {
                // Define a regex pattern to match the productDetail
                val pattern = "productDetail.*".toRegex()
                return pattern.matches(route)
            }
        }

    }
}

sealed class TopLevelDestination(
    val route: String,
    val title: Int? = null,
    val navArguments: List<NamedNavArgument> = emptyList(),
)
