package com.example.finalproject.data

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

interface AuthRepository {
    val currentUser: FirebaseUser?
    val storageRef: StorageReference?
    val db: FirebaseFirestore?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
    suspend fun getProfilePhoto() : StorageReference
}