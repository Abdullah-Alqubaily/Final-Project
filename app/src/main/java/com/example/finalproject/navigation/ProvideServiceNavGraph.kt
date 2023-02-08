package com.example.finalproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.ProvideServiceScreen

fun NavGraphBuilder.provideServiceNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
) {
    navigation(
        route = Graph.ProvideService.route,
        startDestination = ProvideServiceNavGraph.ProvideService.route
    ){
        composable(route = ProvideServiceNavGraph.ProvideService.route) {
            ProvideServiceScreen(
                userViewModel = userViewModel,
                onClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
sealed class ProvideServiceNavGraph(val route: String) {
    object ProvideService : ProvideServiceNavGraph(route = "Service Details")
}