package com.example.finalproject.ui.main.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
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
import com.example.finalproject.ui.main.profile.components.TickItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvideServiceScreen(

) {
    ProvideServiceContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvideServiceContent(

) {

    var phoneNumber by rememberSaveable { mutableStateOf("") }

    var isCheckedBooleanPhotographer by rememberSaveable { mutableStateOf(false) }

    var isCheckedBooleanDesigner by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 32.dp),
            text = "Become a seller",
            fontSize = 24.sp
        )
        TextField(
            value = phoneNumber,
            onValueChange = { newText ->
                phoneNumber = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            label = { Text(text = "Number") },
            placeholder = { Text(text = "Enter your phone number...") },
            trailingIcon = {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "clear text",
                    modifier = Modifier
                        .clickable {
                            phoneNumber = ""
                        }
                )
            }
        )
        TickItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 20.dp),
            text = "Photographer",
            onValueChange = { isChecked ->
                isCheckedBooleanPhotographer = isChecked
            },
            isChecked = isCheckedBooleanPhotographer
        )

        TickItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 20.dp),
            text = "Graphic Designer",
            onValueChange = { isChecked ->
                isCheckedBooleanDesigner = isChecked
            },
            isChecked = isCheckedBooleanDesigner
        )

        Button(
            modifier = Modifier.padding(top = 60.dp),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(20.dp),
        ) {
            androidx.compose.material.Text(
                modifier = Modifier,
                text = "Continue",
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun ProvideServicePrev() {
    ProvideServiceContent()
}
