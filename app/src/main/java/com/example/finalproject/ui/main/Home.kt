package com.example.finalproject.ui.main


import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.navigation.BottomBarRoutes
import com.example.finalproject.navigation.HomeNavGraph
import com.example.finalproject.ui.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController? = rememberNavController(),
    onClick: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController!!) }
    ) {
        it.calculateBottomPadding()
        HomeNavGraph(viewModel = viewModel!!, navController!!) {
            onClick()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(
    navController: NavHostController
) {
    val items = listOf(BottomBarRoutes.Home, BottomBarRoutes.Cart, BottomBarRoutes.Profile)

    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen.route) {
                        "Home" -> Icon(screen.icon!!, contentDescription = screen.title)
                        "Cart" -> Icon(screen.icon!!, contentDescription = screen.title)
                        "Profile" -> Icon(screen.icon!!, contentDescription = screen.title)
                    }
                },
                label = { Text(screen.title!!) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestDisplayName) {
                                inclusive = screen.route === navController.graph.startDestDisplayName
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPrev() {
    HomeScreen(null, navController = null) {}
}
