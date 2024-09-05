package com.example.assigmentloginrd.ui.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assigmentloginrd.db.User
import com.example.assigmentloginrd.ui.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val userList = MutableLiveData<List<User>>()

    fun loadUsers() {
        viewModelScope.launch {
            userList.value = repository.getUsers()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
            loadUsers()
        }
    }
}

