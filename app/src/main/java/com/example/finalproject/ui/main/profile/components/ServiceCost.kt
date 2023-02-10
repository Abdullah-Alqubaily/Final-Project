package com.example.finalproject.ui.main.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ServiceCost(
    modifier : Modifier = Modifier
) {
    Column (
        modifier = Modifier.fillMaxWidth()
            ){
        Text(
            modifier = modifier,
            text = "Service cost"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
          border = BorderStroke(1.dp, color = Color.Black),
            modifier = Modifier.padding(horizontal = 32.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 30.dp),
                text = "15$"
            )
        }
    }
  
}