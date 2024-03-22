package com.example.pchelper.roomdb

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readdata: LiveData<List<user>> = userDao.readdata()
    suspend fun adduser(user: user){
        userDao.addqa(user)
    }
}