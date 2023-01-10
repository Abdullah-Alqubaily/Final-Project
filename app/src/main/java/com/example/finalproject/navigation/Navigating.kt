package com.example.finalproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.auth.LoginScreen
import com.example.finalproject.ui.auth.RegisterScreen
import com.example.finalproject.ui.home.ProfileScreen


@Composable
fun Nav(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {

    val startDestination = viewModel.startDestination.collectAsState().value
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination ,
        modifier = modifier
    ) {
        composable(route = Routes.Login.name) {
            LoginScreen(viewModel = viewModel, onClickedText = {
                navController.navigate(Routes.Register.name) {
                    popUpTo(Routes.Login.name) { inclusive = true }
                }
            }, onSuccess = {
                navController.navigate(Routes.Home.name) {
                    popUpTo(Routes.Login.name) { inclusive = true }
                }
            })
        }

        composable(route = Routes.Home.name) {
            ProfileScreen(viewModel = viewModel) {
                navController.navigate(Routes.Login.name) {
                    popUpTo(Routes.Home.name) { inclusive = true }
                }
            }
        }

        composable(route = Routes.Register.name) {
            RegisterScreen(viewModel = viewModel, onClickedText = {
                navController.navigate(Routes.Login.name) {
                    popUpTo(Routes.Register.name) { inclusive = true }
                }
            }, onSuccess = {
                navController.navigate(Routes.Home.name) {
                    popUpTo(Routes.Register.name) { inclusive = true }
                }
            })
        }
    }
}
