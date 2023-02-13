package com.example.finalproject.ui.auth

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject
import kotlin.math.log

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

//    private val _serviceImages =  MutableStateFlow<Uri?>(null)
//    val : StateFlow<Uri?> = _profilePhoto
    private val _userType = MutableStateFlow<String?>("normal")
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
            getUserJob()
        }
    }

    fun postService(images: List<Uri?>, serviceDes: String, whatIs: String, servicePrice: String) = viewModelScope.launch {
        val user = db?.collection("users")?.document(currentUser!!.uid)
        val serviceImages = mutableListOf<Uri>()
        var count = 0
        for (i in images) {
            storageRef?.child("serviceImages/${currentUser?.uid}${count++}")
                ?.putFile(i!!)?.await()?.storage?.downloadUrl?.addOnSuccessListener {
                    serviceImages.add(it)
                }?.await()
            Log.d("pello", serviceImages.toString())
        }
        Log.d("fello", serviceImages.toString())

        user?.update("serviceImg", serviceImages)
        user?.update("serviceDes", serviceDes)
        user?.update("whatIsTheService", whatIs)
        user?.update("servicePrice", servicePrice)
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


    fun updateUserProfile(service: String, phone: String) = viewModelScope.launch{
        val user = db?.collection("users")?.document(currentUser!!.uid)
        user?.update("userType", service)
            ?.addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            ?.addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        user?.update("phoneNum", phone)
    }

    fun getProfilePhoto() = viewModelScope.launch {
        val result = repository.getProfilePhoto()
        result.downloadUrl.addOnSuccessListener {
            _profilePhoto.value = it
            Log.d("hello", it.toString())
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
        getUserJob()

    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result

        val user = UserUiState(
            id = Firebase.auth.currentUser?.uid,
            name = Firebase.auth.currentUser?.displayName,
            userType = "normal",
        )
        db?.collection("users")?.document(Firebase.auth.currentUser!!.uid)?.set(user)
        getUserJob()

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
