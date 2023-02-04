package com.example.finalproject.ui.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.auth.UserViewModel

@Composable
fun ProfileInfoScreen(
    userViewModel: UserViewModel?,
    onLogOut: () -> Unit
) {
    ProfileInfoContent(userViewModel = userViewModel) {
        onLogOut()
    }
}

@Composable
fun ProfileInfoContent(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel?,
    onLogOut: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Row() {
            Text(text = stringResource(id = R.string.username))
            Text(
                text = userViewModel?.currentUser?.displayName ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(0.7F).padding(start = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row() {
            Text(text = stringResource(id = R.string.email))
            Text(
                text = userViewModel?.currentUser?.email ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(0.7f).padding(start = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Button(
                onClick = {
                    userViewModel?.logout()
                    onLogOut()
                },
                modifier = Modifier
            ) {
                Text(text = stringResource(id = R.string.logout))
            }
    }


}