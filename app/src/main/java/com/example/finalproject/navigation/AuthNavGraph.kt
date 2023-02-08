package com.example.finalproject.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.auth.LoginScreen
import com.example.finalproject.ui.auth.RegisterScreen
import com.example.finalproject.ui.main.BottomNavScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
) {

//    val startDestination = viewModel.startDestination.value

    navigation(
        route = Graph.Authentication.route,
        startDestination = Graph.MainHome.route
    ) {
        composable(route = AuthRoutes.Login.route) {
            LoginScreen(userViewModel = userViewModel,
                onClickedText = {
                    navController.navigate(AuthRoutes.Register.route) {
                    popUpTo(AuthRoutes.Login.route) { inclusive = false }
                }
            }, onSuccess = {
                    userViewModel.getProfilePhoto()
                navController.popBackStack()
                navController.navigate(Graph.MainHome.route) {
                    popUpTo(Graph.MainHome.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = AuthRoutes.Register.route) {
            RegisterScreen(viewModel = userViewModel,
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
            BottomNavScreen(
                userViewModel = userViewModel,
                onLogInBtn = {
                    navController.navigate(AuthRoutes.Login.route)
                },
                onServiceClicked = {
                    navController.navigate(Graph.Details.route)
                },
                onProvideServiceClicked = {
                    navController.navigate(Graph.ProvideService.route)
                }
            )
        }
    }
}

sealed class AuthRoutes(val route: String) {
    object Login : AuthRoutes(route = "Login")
    object Register : AuthRoutes(route = "Register")
}
