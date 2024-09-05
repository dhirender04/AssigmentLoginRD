package com.example.assigmentloginrd.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigmentloginrd.ui.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginSuccess = MutableLiveData<Boolean>()

    fun login() {
        viewModelScope.launch {
            val user = repository.login(email.value ?: "", password.value ?: "")
            loginSuccess.value = user != null
        }
    }
}

