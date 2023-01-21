package com.example.finalproject.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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
    viewModel: AuthViewModel?,
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
        onEmailChange = { email = it },
        onPassChange = { password = it },
        onSuccess = onSuccess
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    viewModel: AuthViewModel?,
    name: String,
    email: String,
    password: String,
    signupFlow: State<Resource<FirebaseUser>?>?,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
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


            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = {
                    Text(text = stringResource(id = R.string.name))
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

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = {
                    Text(text = stringResource(id = R.string.email))
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

            OutlinedTextField(
                value = password,
                onValueChange = onPassChange,
                label = {
                    Text(text = stringResource(id = R.string.password))
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
                    viewModel?.signup(name, email, password)
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
                    style = MaterialTheme.typography.titleMedium
                )
            }


            Box(Modifier.constrainAs(refLoader) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Status(
                    UserState = signupFlow,
                ) {
                    onSuccess()
                }
            }
        }

    }
}
