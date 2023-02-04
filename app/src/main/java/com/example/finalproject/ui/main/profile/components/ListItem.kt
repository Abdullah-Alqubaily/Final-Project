package com.example.finalproject.ui.main.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null,
    @DrawableRes
    icon: Int,
    onClick: () -> Unit = {}
) {

    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineText = {
            when(color) {
                null -> Text(text = text)
                else -> Text(text = text, color = color)
            }
        },
        leadingContent = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Localized description",
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Arrow"
            )
        }
    )
}