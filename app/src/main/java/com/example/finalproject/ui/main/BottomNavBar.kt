package com.example.finalproject.ui.main


import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.example.finalproject.ui.auth.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen(
    userViewModel: UserViewModel?,
    navController: NavHostController? = rememberNavController(),
    onLogInBtn: () -> Unit,
    onServiceClicked: () -> Unit,
    onMakePostClicked: () -> Unit,
    onProvideServiceClicked: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController!!) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            BottomNavGraph(
                userViewModel = userViewModel,
                navController = navController!!,
                onLogInBtn = onLogInBtn,
                onServiceClicked = onServiceClicked,
                onMakePostClicked = onMakePostClicked,
            ) {
                onProvideServiceClicked()
            }
        }
    }
}


@Composable
fun BottomNavigation(
    navController: NavHostController
) {
    val items = listOf(BottomBarRoutes.Home, BottomBarRoutes.Favorite, BottomBarRoutes.Profile)

    NavigationBar(
        modifier = Modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen.route) {
                        "Home" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                        "Favorite" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                        "Profile" -> screen.icon?.let { Icon(it, contentDescription = screen.title) }
                    }
                },
                label = { screen.title?.let {
                    when (it) {
                        "Home" ->  { Text(text = stringResource(id = R.string.home)) }
                        "Favorite" -> { Text(text = stringResource(id = R.string.favorite)) }
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
    BottomNavScreen(null, navController = null,{}, {},{}) {}
}
