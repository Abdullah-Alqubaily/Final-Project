package com.example.finalproject.ui.auth

import android.widget.Toast
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.example.finalproject.data.Resource
import com.google.firebase.auth.FirebaseUser

@Composable
fun Status(
    UserState: State<Resource<FirebaseUser>?>?,
    onSuccess: () -> Unit
) {
    UserState?.value?.let {
        when (it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
            }
            Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    onSuccess()
                }
            }
        }
    }
}