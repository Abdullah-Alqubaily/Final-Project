package com.example.finalproject.ui.main.profile

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.components.TickItem

@Composable
fun ProvideServiceScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel?,
    onClick: () -> Unit
) {
    ProvideServiceContent(
        userViewModel = userViewModel,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvideServiceContent(
    userViewModel: UserViewModel?,
    onClick: () -> Unit
) {

    var phoneNumber by remember { mutableStateOf("") }

    var isCheckedBooleanPhotographer by remember { mutableStateOf(false) }

    var isCheckedBooleanDesigner by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val focusManager = LocalFocusManager.current

    var bio by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 32.dp
            ),
            text = "Become a service provider",
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
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
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
            onClick = {
                userViewModel?.getUserJob()
                if (isCheckedBooleanDesigner && isCheckedBooleanPhotographer && phoneNumber.isNotEmpty()) {
                    userViewModel?.updateUserProfile("Photographer/Designer", phoneNumber)
                    onClick()
                } else if (isCheckedBooleanPhotographer && phoneNumber.isNotEmpty()) {
                    userViewModel?.updateUserProfile("Photographer", phoneNumber)
                    onClick()
                } else if (isCheckedBooleanDesigner && phoneNumber.isNotEmpty()) {
                    userViewModel?.updateUserProfile("Designer", phoneNumber)
                    onClick()
                } else {
                    Toast.makeText(context, "Phone number is empty or select box is empty", LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(20.dp),
        ) {
            Text(
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
    ProvideServiceContent(null) {}
}
