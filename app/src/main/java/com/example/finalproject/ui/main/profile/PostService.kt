package com.example.finalproject.ui.main.profile

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.finalproject.ui.auth.UserViewModel
import com.example.finalproject.ui.main.profile.components.AnnouncementSection

@Composable
fun PostServiceScreen(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {
    PostServiceContent(
        userViewModel = userViewModel,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostServiceContent(
    userViewModel: UserViewModel,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    var infoAboutService by remember {
        mutableStateOf("")
    }
    var serviceDescription by remember {
        mutableStateOf("")
    }

    var servicePrice by remember {
        mutableStateOf("")
    }

    var selectedImages by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val focusManager = LocalFocusManager.current

    val multiPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uriList ->
        if (uriList.size == 3) {
            selectedImages = uriList
        } else {
            Toast.makeText(context, "Please chose 3 images", Toast.LENGTH_SHORT).show()
        }

    }



    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
            text = "Add an announcement",
            fontSize = 24.sp
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (selectedImages.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier
                                .size(220.dp)
                                .clickable {
                                    multiPhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },

                            ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    modifier = Modifier.padding(6.dp),
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = ""
                                )
                                Text(text = "Add Image")
                            }
                        }
                    }
                }


                items(selectedImages) {
                    Card(
                        modifier = Modifier
                            .size(220.dp)
                            .clickable {
                                multiPhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = it,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }




        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        AnnouncementSection(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp),
            intro = "What is the provided service?",
            info = "",
            infoText = infoAboutService,
            infoOnValueChange = { newValue ->
                infoAboutService = newValue
            },
            onClickIcon = {
                infoAboutService = ""
            }
        )


        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        AnnouncementSection(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp),
            intro = "Service Description",
            info = "",
            infoText = serviceDescription,
            infoOnValueChange = { newValue ->
                serviceDescription = newValue
            },
            onClickIcon = {
                serviceDescription = ""
            }
        )

        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Service Price",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp),
            value = servicePrice,
            onValueChange = { servicePrice = it },

            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier,
            shape = RoundedCornerShape(20.dp),
            onClick = {
                if (
                    selectedImages.isNotEmpty() &&
                    infoAboutService.isNotEmpty() &&
                    serviceDescription.isNotEmpty() &&
                    servicePrice.isNotEmpty()
                ) {
                    userViewModel.postService(
                        selectedImages,
                        serviceDescription,
                        infoAboutService,
                        servicePrice
                    )
                    onClick()
                } else {
                    Toast.makeText(context, "Some fields are empty", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Done")
        }

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Preview
@Composable
fun PostServicePrev() {
//    PostServiceContent()
}