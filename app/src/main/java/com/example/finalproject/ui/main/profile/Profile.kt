package com.example.finalproject.ui.main.profile

import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.finalproject.R
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.components.ListItemComponent
import com.example.finalproject.ui.theme.spacing


@Composable
fun ProfileScreen(
    userViewModel: UserViewModel?,
    onLogInBtn: () -> Unit,
    onProfileInfoClicked: () -> Unit
) {
    val profilePhoto = userViewModel?.profilePhoto?.collectAsState()


    ProfileContent(
        userViewModel = userViewModel,
        profilePhoto,
        onLogInBtn,
    ) {
        onProfileInfoClicked()
    }
}

@Composable
fun ProfileContent(
    userViewModel: UserViewModel?,
    profilePhoto: State<Uri?>?,
    onLogInBtn: () -> Unit,
    onProfileInfoClicked: () -> Unit
) {

    val context = LocalContext.current


    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            userViewModel?.storageRef?.child("profile_images/${userViewModel.currentUser?.uid}")
                ?.putFile(uri)
                ?.addOnFailureListener {
                    Toast.makeText(context, it.message, LENGTH_SHORT).show()
                }?.addOnSuccessListener {
                    Toast.makeText(context, "Upload successfully", LENGTH_SHORT).show()
                    userViewModel.getProfilePhoto()
                }
        }
    }


    val spacing = MaterialTheme.spacing

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(enabled = userViewModel?.currentUser != null) {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    shape = CircleShape


                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        model = profilePhoto?.value,
                        error = painterResource(id = R.drawable.ic_baseline_person_24),
                        contentDescription = "d",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = userViewModel?.currentUser?.displayName ?: "Guest",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            ) {
            when(userViewModel?.currentUser) {
                null -> {
                    ListItemComponent(text = "Sign in", icon = R.drawable.login) {
                        onLogInBtn()
                    }
                    Divider()
                }
                else -> {
                    ListItemComponent(
                        text = "Become a service provider",
                        color = MaterialTheme.colorScheme.primary,
                        icon = R.drawable.login
                    )
                    Divider()
                }
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            ListItemComponent(text = "Language", icon = R.drawable.language)
            Divider()

            if (userViewModel?.currentUser != null) {
                ListItemComponent(text = "Profile info", icon = R.drawable.account_box) {
                    onProfileInfoClicked()
                }
                Divider()
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = "Resources",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            ListItemComponent(text = "Support", icon = R.drawable.support)
            Divider()
            ListItemComponent(text = "Privacy policy", icon = R.drawable.lock)
        }
    }

}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPrv() {
    ProfileScreen(null, {},{})
}
