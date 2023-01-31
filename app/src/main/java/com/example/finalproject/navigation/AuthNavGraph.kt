package com.example.finalproject.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.auth.LoginScreen
import com.example.finalproject.ui.auth.RegisterScreen
import com.example.finalproject.ui.main.BottomNavScreen



fun NavGraphBuilder.authNavGraph(
    navController: NavHostController, viewModel: AuthViewModel
) {

//    val startDestination = viewModel.startDestination.value

    navigation(
        route = Graph.Authentication.route,
        startDestination = Graph.MainHome.route
    ) {
        composable(route = AuthRoutes.Login.route) {
            LoginScreen(viewModel = viewModel,
                onClickedText = {
                    navController.navigate(AuthRoutes.Register.route) {
                    popUpTo(AuthRoutes.Login.route) { inclusive = false }
                }
            }, onSuccess = {
                navController.popBackStack()
                navController.navigate(Graph.MainHome.route) {
                    popUpTo(Graph.MainHome.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = AuthRoutes.Register.route) {
            RegisterScreen(viewModel = viewModel,
            onSuccess = {
                navController.popBackStack()
                navController.navigate(Graph.MainHome.route) {
                    popUpTo(Graph.MainHome.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = Graph.MainHome.route) {
            BottomNavScreen(viewModel) {
                navController.navigate(AuthRoutes.Login.route)
            }
        }
    }
}

sealed class AuthRoutes(val route: String) {
    object Login : AuthRoutes(route = "Login")
    object Register : AuthRoutes(route = "Register")
}
