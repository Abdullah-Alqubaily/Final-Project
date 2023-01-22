package com.example.finalproject.ui.auth

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.example.finalproject.data.Resource
import com.google.firebase.auth.FirebaseUser

@Composable
fun Status(
    clearStatus: () -> Unit,
    userState: State<Resource<FirebaseUser>?>?,
    onSuccess: () -> Unit
) {

    userState?.value?.let {
        when (it) {
            is Resource.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.exception.message, LENGTH_SHORT).show()
                clearStatus()
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


//@Composable
//fun SnackBarErr() {
//    val scaffoldState = rememberScaffoldState()
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//
//    }
//}

