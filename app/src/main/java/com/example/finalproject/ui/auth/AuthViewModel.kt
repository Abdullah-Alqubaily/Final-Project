package com.example.finalproject.ui.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.data.AuthRepository
import com.example.finalproject.data.Resource
import com.example.finalproject.data.UserUiState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    private val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()

//    private val _startDestination = MutableStateFlow(AuthRoutes.Login.route)
//    val startDestination: StateFlow<String> get() = _startDestination

    init {
//        _startDestination.value = Graph.MainHome.route
        if(repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
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
            bio ="hello my name is ${Firebase.auth.currentUser?.displayName}",
        )
        db.collection("users").document(Firebase.auth.currentUser!!.uid).set(user)

    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }

    fun checkStatus() {
        _loginFlow.value = null
        _signupFlow.value = null
    }
}
