package com.example.finalproject.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.auth.LoginScreen
import com.example.finalproject.ui.main.CartScreen
import com.example.finalproject.ui.main.Content
import com.example.finalproject.ui.main.HomeScreen
import com.example.finalproject.ui.main.ProfileScreen


@Composable
fun HomeNavGraph(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    NavHost(
        navController = navController,
        route = Graph.MainHome.route,
        startDestination = BottomBarRoutes.Home.route,
        modifier = modifier
    ) {

        composable(BottomBarRoutes.Home.route) {
            Content()
        }

        composable(BottomBarRoutes.Cart.route) {
            CartScreen()
        }

        composable(BottomBarRoutes.Profile.route) {
            ProfileScreen(viewModel = viewModel) {
                onClick()
            }
        }
    }
}
