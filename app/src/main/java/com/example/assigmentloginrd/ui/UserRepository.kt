package com.example.assigmentloginrd.ui

import com.example.assigmentloginrd.db.User
import com.example.assigmentloginrd.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }
}

