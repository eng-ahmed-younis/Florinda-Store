package com.florinda.store.navigation

sealed class Router(val route: String){
    object SplashScreen: Router("splash_screen")
    object OnBoardingScreen: Router("on_boarding_screen")
    object WelcomeScreen : Router("welcome_screen")
    object LoginScreen : Router("login_screen")
    object RegistrationScreen : Router("register_screen")
    object OtpVerifyScreen : Router("otp_verify_screen")
    object ForgotPasswordScreen : Router("forgot_password_screen")
    object HomeScreen : Router("home_screen")
    object FavoriteScreen : Router("favorite_screen")
    object SearchScreen : Router("search_screen")
    object CardScreen : Router("card_screen")
    object OrdersScreen : Router("orders_screen")
    object TrackOrderScreen : Router("track_order_screen")
    object SavesScreen : Router("saves_screen")
    object ProfileScreen : Router("profile_screen")
}
