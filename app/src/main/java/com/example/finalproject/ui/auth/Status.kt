package com.example.finalproject.ui.auth

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
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
                Toast.makeText(context, it.exception.message, LENGTH_SHORT).show()
            }
            is Resource.Loading -> {
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

