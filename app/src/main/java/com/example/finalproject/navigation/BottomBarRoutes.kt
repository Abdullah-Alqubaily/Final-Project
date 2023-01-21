package com.example.finalproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarRoutes(val route: String, val title: String? = null, val icon: ImageVector? = null) {
    // Bottom navigation Routes
    object Home : BottomBarRoutes(route = "Home", title = "Home", icon = Icons.Filled.Home)
    object Profile : BottomBarRoutes(route = "Profile", title = "Profile", icon = Icons.Filled.Person)
    object Cart : BottomBarRoutes(route = "Cart", title = "Cart", icon = Icons.Filled.ShoppingCart)

}
