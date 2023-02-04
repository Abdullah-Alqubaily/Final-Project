package com.example.finalproject.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.FavoriteScreen
import com.example.finalproject.ui.main.home.HomeScreen
import com.example.finalproject.ui.main.profile.ProfileInfoScreen
import com.example.finalproject.ui.main.profile.ProfileScreen


@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel?,
    navController: NavHostController,
    onLogInBtn: () -> Unit,
    onServiceClicked: () -> Unit
) {

    NavHost(
        navController = navController,
        route = Graph.MainHome.route,
        startDestination = BottomBarRoutes.Home.route,
        modifier = modifier
    ) {

        composable(BottomBarRoutes.Home.route) {
            HomeScreen {
                onServiceClicked()
            }
        }

        composable(BottomBarRoutes.Favorite.route) {
            FavoriteScreen()
        }

        composable(BottomBarRoutes.Profile.route) {
            ProfileScreen(userViewModel = userViewModel, onLogInBtn
            ){
                navController.navigate(BottomBarRoutes.ProfileInfo.route)
            }
        }

        composable(BottomBarRoutes.ProfileInfo.route) {
            ProfileInfoScreen(userViewModel = userViewModel) {
                navController.popBackStack()
                navController.navigate(BottomBarRoutes.Home.route) {
                    popUpTo(BottomBarRoutes.Home.route) {
                        inclusive = navController.currentDestination?.route === navController.graph.startDestDisplayName
                    }
                }
            }
        }

    }
}
