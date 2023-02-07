package com.example.finalproject.ui.main.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun TickItem(
    modifier : Modifier = Modifier,
    text : String,
    onValueChange : (Boolean) -> Unit,
    isChecked : Boolean
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text
        )
        Checkbox(
            checked = isChecked, onCheckedChange = onValueChange
        )
    }
}