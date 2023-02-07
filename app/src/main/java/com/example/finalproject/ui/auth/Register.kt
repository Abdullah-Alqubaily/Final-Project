package com.example.finalproject.ui.auth

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.finalproject.R
import com.example.finalproject.data.Resource
import com.example.finalproject.ui.theme.spacing
import com.google.firebase.auth.FirebaseUser


@Composable
fun RegisterScreen(
    viewModel: UserViewModel?,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signupFlow = viewModel?.signupFlow?.collectAsState()

    RegisterContent(
        viewModel = viewModel,
        name = name,
        email = email,
        password = password,
        signupFlow = signupFlow,
        onNameChange = { name = it},
        onNameReset = { name = "" },
        onEmailChange = { email = it },
        onEmailReset = { email = "" },
        onPassChange = { password = it },
        onPassReset = { password = "" },
        onSuccess = onSuccess
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    viewModel: UserViewModel?,
    name: String,
    email: String,
    password: String,
    signupFlow: State<Resource<FirebaseUser>?>?,
    onNameChange: (String) -> Unit,
    onNameReset: () -> Unit,
    onEmailChange: (String) -> Unit,
    onEmailReset: () -> Unit,
    onPassChange: (String) -> Unit,
    onPassReset: () -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (
            refParent,
            refName, refEmail,
            refPassword, refButtonSignup,
            refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        ConstraintLayout(
            modifier = Modifier
                .constrainAs(refParent) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
        ) {


            TextField(
                value = name,
                onValueChange = onNameChange,
                label = {
                    Text(text = stringResource(id = R.string.username))
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onNameReset()
                            }
                    )
                },
                modifier = Modifier.constrainAs(refName) {
                    top.linkTo(parent.top, spacing.extraLarge)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            TextField(
                value = email,
                onValueChange = onEmailChange,
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onEmailReset()
                            }
                    )
                },
                modifier = Modifier.constrainAs(refEmail) {
                    top.linkTo(refName.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )

            TextField(
                value = password,
                onValueChange = onPassChange,
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                onPassReset()
                            }
                    )
                },
                modifier = Modifier.constrainAs(refPassword) {
                    top.linkTo(refEmail.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            Button(
                onClick = {
                    if (name.isNotEmpty()) {
                        viewModel?.signup(name, email, password)
                    } else {
                        Toast.makeText(context, "Username is empty", LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.constrainAs(refButtonSignup) {
                    top.linkTo(refPassword.bottom, spacing.large)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                    width = Dimension.fillToConstraints
                }
            ) {
                Text(
                    text = stringResource(id = R.string.signup),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Box(Modifier.constrainAs(refLoader) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Status(
                clearStatus = {viewModel?.checkStatus()},
                userState = signupFlow
            ) {
                onSuccess()
            }
        }

    }
}
