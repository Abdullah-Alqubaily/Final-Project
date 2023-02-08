package com.example.finalproject.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.ui.main.home.ServiceCard

@Composable
fun FavoriteScreen(
    onClick: () -> Unit
) {
    FavoriteContent(
        onClick = onClick
    )
}

@Composable
fun FavoriteContent(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 35.dp)
        ){
            Text(
                text = "Favorite list ",
                fontSize = 24.sp
            )
            Icon(imageVector = Icons.Default.Favorite , contentDescription = "favorite icon")
        }

        LazyColumn(
            modifier = Modifier.padding(top = 50.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(10) {
                ServiceCard(
                    modifier = Modifier,
                ) {
                    onClick()
                }

            }
        }
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun FavoriteScreenPrev() {
    FavoriteContent {}
}
