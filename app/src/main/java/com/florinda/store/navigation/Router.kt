package com.florinda.store.navigation

sealed class Router(val route: String){
     data object SplashScreen: Router("splash_screen")
     data object OnBoardingScreen: Router("on_boarding_screen")
     data object WelcomeScreen : Router("welcome_screen")
     data object LoginScreen : Router("login_screen")
     data object RegistrationScreen : Router("register_screen")
     data object OtpVerifyScreen : Router("otp_verify_screen")
     data object ForgotPasswordScreen : Router("forgot_password_screen")
     data object HomeScreen : Router("home_screen")
     data object FavoriteScreen : Router("favorite_screen")
     data object SearchScreen : Router("search_screen")
     data object CardScreen : Router("card_screen")
     data object OrdersScreen : Router("orders_screen")
     data object TrackOrderScreen : Router("track_order_screen")
     data object SavesScreen : Router("saves_screen")
     data object ProfileScreen : Router("profile_screen")
}
