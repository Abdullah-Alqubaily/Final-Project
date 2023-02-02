package com.example.finalproject.ui.main


import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.R
import com.example.finalproject.navigation.BottomBarRoutes
import com.example.finalproject.navigation.BottomNavGraph
import com.example.finalproject.ui.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen(
    authViewModel: AuthViewModel?,
    navController: NavHostController? = rememberNavController(),
    onLogInBtn: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController!!) }
    ) {
        it.calculateBottomPadding()
        BottomNavGraph(authViewModel = authViewModel,  navController!!) {
            onLogInBtn()
        }
    }
}


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
                        "Home" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                        "Cart" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                        "Profile" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                    }
                },
                label = { screen.title?.let {
                    when (it) {
                        "Home" ->  { Text(text = stringResource(id = R.string.home)) }
                        "Cart" -> { Text(text = stringResource(id = R.string.cart)) }
                        "Profile" ->  { Text(text = stringResource(id = R.string.profile)) }
                    }}
                 },
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
fun BottomNavPrev() {
    BottomNavScreen(null, navController = null) {}
}
