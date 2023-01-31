package com.example.finalproject.ui.main

import android.content.res.Configuration
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.finalproject.R
import com.example.finalproject.ui.auth.AuthViewModel
import com.example.finalproject.ui.theme.spacing
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

object Storage {
    val ref = FirebaseStorage.getInstance().reference
}

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel?,
    onLogOut: () -> Unit,
    onLogInBtn: () -> Unit
) {
    ProfileContent(
        viewModel = viewModel, onLogOut
    ) {
        onLogInBtn()
    }
}

@Composable
fun ProfileContent(
    viewModel: AuthViewModel?,
    onLogOut: () -> Unit,
    onLogInBtn: () -> Unit
) {

    val context = LocalContext.current
    var uploadTask: UploadTask?
    val isLoading by remember {
        mutableStateOf<Boolean?>(null)
    }

//    if (viewModel?.currentUser != null) {
//        storageViewModel?.storage?.reference?.child("profile_images/${viewModel.currentUser?.uid}")
//            ?.downloadUrl?.addOnSuccessListener {
//
//            }?.addOnFailureListener {
//
//            }?.addOnCompleteListener {
//                storageViewModel.show(it.result)
//            }
//    }




    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            uploadTask =
                Storage.ref
                    .child("profile_images/${viewModel?.currentUser?.uid}")
                    .putFile(uri)
            uploadTask?.addOnFailureListener {
                // Handle unsuccessful uploads
                Toast.makeText(context, it.message, LENGTH_SHORT).show()
            }?.addOnSuccessListener { _ ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(context, "Upload successfully", LENGTH_SHORT).show()

            }?.addOnProgressListener {

            }

        }
    }




    val spacing = MaterialTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(spacing.medium)
            .padding(top = spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.welcome_back),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = viewModel?.currentUser?.displayName ?: "",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )


        Box(
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .clickable(enabled = viewModel?.currentUser != null) {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                shape = CircleShape


                ) {
                if (isLoading == true) {
                    CircularProgressIndicator()
                }
                AsyncImage(
                    modifier = Modifier.size(128.dp),
                    model = "",
                    error = painterResource(id = R.drawable.ic_baseline_person_24),
                    fallback = painterResource(id = R.drawable.ic_baseline_person_24),
                    contentDescription = "d",
                )
            }
        }






        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.username),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.3f),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = viewModel?.currentUser?.displayName ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.7f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = spacing.extraLarge)
            ) {
                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.3f),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = viewModel?.currentUser?.email ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.7f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (viewModel?.currentUser == null) {
                Button(
                    onClick = {
                        onLogInBtn()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = spacing.medium)
                ) {
                    Text(text = stringResource(id = R.string.login))
                }
            } else {
                Button(
                    onClick = {
                        viewModel.logout()
                        onLogOut()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = spacing.medium)
                ) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }


        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPrv() {
    ProfileScreen(null,{}){}
}
