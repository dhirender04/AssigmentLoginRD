package com.example.assigmentloginrd.ui.addUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigmentloginrd.db.User
import com.example.assigmentloginrd.ui.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserFormViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _saveUserEvent = MutableLiveData<Boolean>()
    val saveUserEvent: LiveData<Boolean> get() = _saveUserEvent

    fun saveUser() {
        val user = User(
            name = name.value ?: "",
            email = email.value ?: "",
            password = password.value ?: ""
        )
        viewModelScope.launch {
            repository.insertUser(user)
            _saveUserEvent.value = true
        }
    }
}
