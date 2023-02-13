package com.example.finalproject.data

import android.net.Uri

data class PostUiState(
    val id: String? = null,
    val name: String? = null,
    val phoneNum: String? = null,
    val userType: String? = null,
    val serviceDes: String? = null,
    val whatIsTheService: String? = null,
    val servicePrice: String? = null,
    val serviceImg: List<Uri?>? = null,
)
