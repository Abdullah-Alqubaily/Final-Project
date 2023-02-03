package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.auth.UserViewModel


@Composable
fun Nav(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.Root.route,
        startDestination = Graph.Authentication.route
    ) {
        authNavGraph(navController, userViewModel)
        serviceNavGraph(navController, userViewModel)
    }
}

sealed class Graph(val route: String) {
    object Root : Graph(route = "Root")
    object Authentication : Graph(route = "Authentication")
    object MainHome : Graph(route = "MainHome")
    object Details : Graph(route = "Details")
}