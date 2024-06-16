package com.florinda.store.navigation.enhance

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.florinda.store.R

private object Routes {

    // authentication
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val OTP = "otp"
    const val FORGET_PASSWORD = "forget_password"

    // Main
    const val MAIN = "main"
    const val HOME = "home"
    const val FAVORITE = "favorite"
    const val SEARCH = "search"
    const val CARD = "card"
    const val ORDER = "order"
    const val PROFILE = "profile"
    const val NOTIFICATION = "notification"

    // Welcome
    const val SPLASH = "splash"
    const val ON_BOARD = "on_board"
    const val WELCOME = "welcome"

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
    data object Auth : AppScreen(Routes.AUTH) {
        data object Login : AppScreen(Routes.LOGIN)
        data object Register : AppScreen(Routes.REGISTER)
        data object Otp : AppScreen(Routes.OTP)
        data object ForgetPassword : AppScreen(Routes.FORGET_PASSWORD)
    }

    data object Welcome : AppScreen(Routes.WELCOME) {
        data object Splash : AppScreen(Routes.SPLASH)
        data object OnBoarding : AppScreen(Routes.ON_BOARD)
        data object Welcome : AppScreen(Routes.WELCOME)
    }


    data object Main : TopLevelDestination(Routes.MAIN) {

        data object Home : TopLevelDestination(
            route = Routes.HOME,
            title = R.string.home,
        )

        data object Notification : TopLevelDestination(
            route = Routes.NOTIFICATION,
            title = R.string.notification,
        )

        data object Profile : TopLevelDestination(
            route = Routes.PROFILE,
            title = R.string.profile
        )

        data object Search : TopLevelDestination(
            route = Routes.SEARCH,
            title = R.string.search,
        )

        data object Favorites : TopLevelDestination(
            route = Routes.FAVORITE,
            title = R.string.favorite
        )


        data object ProductDetail : TopLevelDestination(
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
