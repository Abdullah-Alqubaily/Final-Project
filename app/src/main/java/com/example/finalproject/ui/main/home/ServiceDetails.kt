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

@Composable
fun ServiceDetails() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()).fillMaxSize(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.image),
            contentDescription = "Graphic designer image")

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
                        modifier = Modifier.padding(start = 30.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Star, contentDescription ="Star icon"
                        )
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Star, contentDescription ="Star icon"
                        )
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Star, contentDescription ="Star icon"
                        )
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Star, contentDescription ="Star icon"
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 30.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 15.dp),
                                text = "احمد",
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            CircleImg(
                                size = 50.dp
                            )
                        }

                    }

                }


            }
        }
        /**
         * seperator
         */
        /**
         * seperator
         */
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, end = 16.dp),
            text = "تصميم انفوجرافيك",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            startIndent = 260.dp
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "هل تريد النجاح في الترويج لمنتجاتك او مشروعك الخاص؟"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "اعتقد با ان الجواب \"نعم\" وهنا يأتي دوري كمصمم جرافيكي سوف اقوم"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = " با تقديم اعلان من لأبداع الممزوج  بالشغف يساعدك على رفع مبيعاتك"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = ".وتفاعل على صفحة النشاط التجاري بكم "
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = ":ماسنقدمه لك مقابل في هذه الخدمة"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = " :ماسنقدمه غي في هذه الخدمة"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "تصميم بنر اعلاني بفكرة ابداعية-"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "تسليم العمل خلال يومين (48) ساعة" + "-"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "." +  "تسليم التصميم بمقاس 500 * 500 بكسل" + "-"
        )
        Text(
            modifier = Modifier
                .align(Alignment.End),
            text = "." +  "تسليم العمل بصيغة PNG" + "-"
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(140.dp)
        ){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add to Fav")
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "السعر $15",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}