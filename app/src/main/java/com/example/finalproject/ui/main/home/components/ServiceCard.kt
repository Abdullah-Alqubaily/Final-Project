package com.example.finalproject.ui.main.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.R

@Composable
fun ServiceCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(
            onClick = onClick
        ),
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
                .fillMaxSize()
                .background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
            ) {

                Image(
                    modifier = Modifier.size(150.dp),
                    bitmap = ImageBitmap.imageResource(
                        id = R.drawable.image
                    ),
                    contentDescription = "Graphic designer image"
                )
                Column(
                    modifier = Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(25.dp)
                                .padding(top = 10.dp, start = 16.dp),
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Star"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = "4.5",
                            fontSize = 15.sp
                        )

                    }
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        text = stringResource(id = R.string.content_description),
                        fontSize = 17.sp
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp),
                        text = stringResource(id = R.string.start_from),
                        fontSize = 17.sp
                    )


                }
            }


        }

    }
}


@Composable
fun CircleImg(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
) {
    Card(
        modifier = modifier.size(size),
        shape = CircleShape,
    ) {
        Image(
            painter = painterResource(id = R.drawable.man),
            contentDescription = "Profile Picture"
        )
    }
}