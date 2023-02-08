package com.example.finalproject.ui.auth

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.AuthRepository
import com.example.finalproject.data.Resource
import com.example.finalproject.data.UserUiState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    private val _profilePhoto = MutableStateFlow<Uri?>(null)
    val profilePhoto: StateFlow<Uri?> = _profilePhoto

    private val _userType = MutableStateFlow<String?>(null)
    val userType: StateFlow<String?> = _userType

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    val storageRef: StorageReference?
        get() = repository.storageRef

    val db: FirebaseFirestore?
        get() = repository.db

//    private val _startDestination = MutableStateFlow(AuthRoutes.Login.route)
//    val startDestination: StateFlow<String> get() = _startDestination

    init {
//        _startDestination.value = Graph.MainHome.route
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
            getProfilePhoto()
        }
    }

    fun getUserJob()  = viewModelScope.launch{
        val docRef = db?.collection("users")?.document(currentUser!!.uid)
        docRef?.get()
            ?.addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("get", "DocumentSnapshot data: ${document.data}")
                    _userType.value = document.data?.get("userType").toString()
                } else {
                    Log.d("get", "No such document")
                }
            }
            ?.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }

    fun updateUserProfile(service: String) = viewModelScope.launch{
        val user = db?.collection("users")?.document(currentUser!!.uid)
        user!!.update("userType", service)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    fun getProfilePhoto() = viewModelScope.launch {
        val result = repository.getProfilePhoto()
        result.downloadUrl.addOnSuccessListener {
            _profilePhoto.value = it
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result

    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result

        val user = UserUiState(
            id = Firebase.auth.currentUser?.uid,
            name = Firebase.auth.currentUser?.displayName,
            userType = "normal",
            bio = "hello my name is ${Firebase.auth.currentUser?.displayName}",
        )
        db?.collection("users")?.document(Firebase.auth.currentUser!!.uid)?.set(user)

    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
        _profilePhoto.value = null
    }

    fun checkStatus() {
        _loginFlow.value = null
        _signupFlow.value = null
    }
}
