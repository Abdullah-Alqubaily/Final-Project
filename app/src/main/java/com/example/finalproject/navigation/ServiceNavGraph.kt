package com.example.finalproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.home.ServiceDetails

fun NavGraphBuilder.serviceNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
) {
    navigation(
        route = Graph.Details.route,
        startDestination = ServiceRoutes.ServiceDetails.route
    ){
        composable(route = ServiceRoutes.ServiceDetails.route) {
            ServiceDetails()
        }
    }
}
sealed class ServiceRoutes(val route: String) {
    object ServiceDetails : ServiceRoutes(route = "Service Details")
}
