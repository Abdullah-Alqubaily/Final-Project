package com.example.finalproject.ui.home


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.theme.spacing

@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    onClicked: () -> Unit
) {
    val spacing = MaterialTheme.spacing
    BottomNavigation()
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when(item) {
                                "Songs" -> Icon(Icons.Filled.Favorite, contentDescription = item)
                                "Artists" -> Icon(Icons.Filled.Home, contentDescription = item)
                                "Playlists" -> Icon(Icons.Filled.Refresh, contentDescription = item)
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) {

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPrev() {
    HomeScreen(null) {}
}
