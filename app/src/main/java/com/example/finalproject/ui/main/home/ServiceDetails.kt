package com.example.finalproject.ui.main.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.R
import com.example.finalproject.ui.main.home.components.CircleImg

@Composable
fun ServiceDetails() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.image),
            contentDescription = "Graphic designer image"
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            shape = RoundedCornerShape(
                topEnd = 0.dp,
                topStart = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = Color.Black
            ),
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CircleImg(
                                size = 50.dp
                            )

                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Ahmed",
                                fontSize = 20.sp,
                            )
                        }

                        Row(
                            modifier = Modifier.padding(start = 30.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Star, contentDescription = "Star icon"
                            )
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Star, contentDescription = "Star icon"
                            )
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Star, contentDescription = "Star icon"
                            )
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Star, contentDescription = "Star icon"
                            )
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Star, contentDescription = "Star icon"
                            )
                        }

                    }

                }

            }
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp),
                text = "Graphic Design",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Divider(
                color = Color.Black,
                thickness = 2.dp,
            )


            Text(
                modifier = Modifier,
                text = "Do you want to sucsuss"
            )

            Text(
                modifier = Modifier,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            )
            Text(
                modifier = Modifier,
                text = " Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            )
            Text(
                modifier = Modifier,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua "
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Add to Fav")
                }
                Text(
                    modifier = Modifier,
                    text = "price $15",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}