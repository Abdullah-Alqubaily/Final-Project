package com.example.finalproject.ui.auth

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.finalproject.R
import com.example.finalproject.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: AuthViewModel?,
    onClickedText: () -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val signupFlow = viewModel?.signupFlow?.collectAsState()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (
            refParent,
            refName, refEmail,
            refPassword, refButtonSignup,
            refTextSignup,
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
                onValueChange = {
                    name = it
                },
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
                onValueChange = {
                    email = it
                },
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
                onValueChange = {
                    password = it
                },
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


            Text(
                modifier = Modifier
                    .constrainAs(refTextSignup) {
                        top.linkTo(refButtonSignup.bottom, spacing.medium)
                        start.linkTo(parent.start, spacing.extraLarge)
                        end.linkTo(parent.end, spacing.extraLarge)
                    }
                    .clickable {
                        onClickedText()
                    },
                text = stringResource(id = R.string.already_have_account),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )


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
