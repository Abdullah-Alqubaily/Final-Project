package com.example.finalproject.ui.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.finalproject.R
import com.example.finalproject.data.Resource
import com.example.finalproject.ui.theme.spacing
import com.google.firebase.auth.FirebaseUser


@Preview(showSystemUi = true)
@Composable
fun LoginPrev() {
    LoginScreen(null, {}, {})
}


@Composable
fun LoginScreen(
    viewModel: AuthViewModel?,
    onClickedText: () -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginFlow = viewModel?.loginFlow?.collectAsState()


    LoginContent(
        viewModel = viewModel,
        email = email,
        password = password,
        loginFlow = loginFlow,
        onEmailChange = { email = it },
        onPassChange = { password = it },
        onClickedText = onClickedText,
        onSuccess = onSuccess
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    viewModel: AuthViewModel?,
    email: String,
    password: String,
    loginFlow: State<Resource<FirebaseUser>?>?,
    onEmailChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onClickedText: () -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current


    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val (
            refParent,
            refEmail,
            refPassword,
            refButtonLogin,
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
                value = email,
                onValueChange = onEmailChange,
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                modifier = Modifier.constrainAs(refEmail) {
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
                value = password,
                onValueChange = onPassChange,
                label = {
                    Text(text = stringResource(id = R.string.password))
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.constrainAs(refPassword) {
                    top.linkTo(refEmail.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
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
                    viewModel?.login(email, password)
                },
                modifier = Modifier.constrainAs(refButtonLogin) {
                    top.linkTo(refPassword.bottom, spacing.large)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                    width = Dimension.fillToConstraints
                }
            ) {
                Text(
                    text = stringResource(id = R.string.login), color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }


            Text(
                modifier = Modifier
                    .constrainAs(refTextSignup) {
                        top.linkTo(refButtonLogin.bottom, spacing.medium)
                        start.linkTo(parent.start, spacing.extraLarge)
                        end.linkTo(parent.end, spacing.extraLarge)
                    }
                    .clickable {
                        onClickedText()
                    },
                text = stringResource(id = R.string.dont_have_account),
                style = MaterialTheme.typography.bodyMedium,
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
                    UserState = loginFlow,
                ) {
                    onSuccess()
                }
            }

        }

    }
}


