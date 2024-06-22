package com.florinda.store.app_component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.florinda.store.navigation.AppScreen

data class BottomNavItem(
    val route: String,
    val icon: ImageVector? = null,
    val contentDescription: String? = null,
    val alertCount: Int? = null,
) {
    companion object {
        val bottomNavItemsList: List<BottomNavItem> = listOf(
            BottomNavItem(
                route = AppScreen.MainGraph.HomeScreen.route,
                icon = Icons.Outlined.Home,
                contentDescription = "Home"
            ),
            BottomNavItem(
                route = AppScreen.MainGraph.FavoritesScreen.route,
                icon = Icons.Outlined.Favorite,
                contentDescription = "Favorite"
            ),
            BottomNavItem(route = ""),
            BottomNavItem(
                route = AppScreen.MainGraph.CardScreen.route,
                icon = Icons.Outlined.ShoppingCart,
                contentDescription = "Order",
                alertCount = 4
            ),
            BottomNavItem(
                route = AppScreen.MainGraph.ProfileScreen.route,
                icon = Icons.Outlined.Person,
                contentDescription = "Profile"
            )
        )
    }
}