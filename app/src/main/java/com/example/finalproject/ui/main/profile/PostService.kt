package com.example.finalproject.ui.main.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.components.AnouncementSection
import com.example.finalproject.ui.main.profile.components.ServiceCost

@Composable
fun PostServiceScreen(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {
    PostServiceContent(
        userViewModel = userViewModel,
        onClick = onClick
    )
}

@Composable
fun PostServiceContent(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {

    var infoAboutService by rememberSaveable {
        mutableStateOf("")
    }
    var serviceDescription by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
            text = "Add an announcement",
            fontSize = 24.sp
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(0.dp),
            backgroundColor = Color(0xFFcecfd0),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 4.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(0.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(1.dp, color = Color.Black)
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            imageVector = Icons.Default.Add, contentDescription = "Add image"
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 1.dp)
                                .align(Alignment.CenterHorizontally),
                            text = "Add image"
                        )
                    }

                }
                Card(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 4.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(0.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(1.dp, color = Color.Black)
                ) {

                }
                Card(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 4.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(0.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(1.dp, color = Color.Black)
                ) {

                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        AnouncementSection(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 32.dp),
            intro = "Introduction to the service",
            info = "Previous Experience",
            infoText = infoAboutService,
            infoOnValueChange = { newValue ->
                infoAboutService = newValue
            },
            onClickIcon = {
                infoAboutService = ""
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        AnouncementSection(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 32.dp),
            intro = "Service Description",
            info = "what is the provided service",
            infoText = serviceDescription,
            infoOnValueChange = { newValue ->
                serviceDescription = newValue
            },
            onClickIcon = {
                serviceDescription = ""
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        ServiceCost(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Preview
@Composable
fun PostServicePrev() {
//    PostServiceContent()
}