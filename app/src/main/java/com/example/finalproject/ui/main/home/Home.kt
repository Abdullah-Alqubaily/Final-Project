package com.example.finalproject.ui.main.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.home.components.ServiceCard

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {
    userViewModel.getServiceInfo()
    Tabs()
    HomeContent(userViewModel = userViewModel) {
        onClick()
    }
}

@Composable
fun HomeContent(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        LazyColumn(
            modifier = Modifier.padding(top = 55.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(10) {
                ServiceCard(
                    modifier = Modifier,
                    userViewModel = userViewModel
                ) {
                    onClick()
                }

            }
        }
    }

}

@Composable
fun Tabs() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf(
        stringResource(id = R.string.photographers),
        stringResource(id = R.string.graphic_designers)
    )
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
    }
}