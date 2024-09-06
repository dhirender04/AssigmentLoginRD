package com.example.assigmentloginrd.ui.login

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
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

    val loginSuccess = MutableLiveData<Boolean>()

    fun login(enteredEmail: String, enteredPassword: String, isFirstTime: Boolean, defaultEmail: String, defaultPassword: String) {
        Log.e(TAG, "login-----: $isFirstTime")
        if (isFirstTime) {
             if (enteredEmail == defaultEmail && enteredPassword == defaultPassword) {
                loginSuccess.value = true
            } else {
                loginSuccess.value = false
            }
        } else {
             viewModelScope.launch {
                val user = repository.login(enteredEmail, enteredPassword)
                loginSuccess.value = user != null
            }
        }
    }
}




