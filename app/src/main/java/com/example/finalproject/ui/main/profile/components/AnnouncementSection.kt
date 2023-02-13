package com.example.finalproject.ui.main.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementSection(
    modifier: Modifier = Modifier,
    intro : String,
    info : String,
    infoText : String,
    infoOnValueChange : (String) -> Unit,
    onClickIcon : () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Text(
        modifier = modifier,
        text = intro,
        fontSize = 20.sp
    )
    Spacer(modifier = Modifier.height(4.dp))

    TextField(
        value = infoText,
        onValueChange = infoOnValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .size(200.dp),
        label = { Text(text = "") },
        placeholder = {  },
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable(
                        onClick = onClickIcon
                    )
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}