package com.example.finalproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.PostServiceScreen
import com.example.finalproject.ui.main.profile.ProvideServiceScreen

fun NavGraphBuilder.makePostNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
) {
    navigation(
        route = Graph.MakePost.route,
        startDestination = MakePostNavGraph.MakePost.route
    ){
        composable(route = MakePostNavGraph.MakePost.route) {
            PostServiceScreen(
                userViewModel = userViewModel,
                onClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
sealed class MakePostNavGraph(val route: String) {
    object MakePost : MakePostNavGraph(route = "Make a post")
}