package com.example.finalproject.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.main.CartScreen
import com.example.finalproject.ui.main.home.HomeScreen
import com.example.finalproject.ui.main.ProfileScreen
import com.example.finalproject.ui.main.home.ServiceDetails


@Composable
fun BottomNavGraph(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onLogInBtn: () -> Unit
) {

    NavHost(
        navController = navController,
        route = Graph.MainHome.route,
        startDestination = BottomBarRoutes.Home.route,
        modifier = modifier
    ) {

        composable(BottomBarRoutes.Home.route) {
            HomeScreen {
                navController.navigate(BottomBarRoutes.ServiceDetails.route)
            }
        }

        composable(BottomBarRoutes.Cart.route) {
            CartScreen()
        }

        composable(BottomBarRoutes.Profile.route) {
            ProfileScreen(viewModel = viewModel, {
                navController.popBackStack()
                navController.navigate(BottomBarRoutes.Home.route) {
                    popUpTo(BottomBarRoutes.Home.route) {
                        inclusive = navController.currentDestination?.route === navController.graph.startDestDisplayName
                    }
                }
            }) {
                onLogInBtn()
            }
        }

        composable(BottomBarRoutes.ServiceDetails.route) {
            ServiceDetails()
        }
    }
}
