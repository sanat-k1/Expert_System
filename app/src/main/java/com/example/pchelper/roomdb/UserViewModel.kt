package com.example.pchelper.roomdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val readdata: LiveData<List<user>>
    init {
        val userDao = UserDatabase.getdatabase(application).userDao()
        repository= UserRepository(userDao)
        readdata = repository.readdata
    }

     fun adduser(user: user){
         viewModelScope.launch(Dispatchers.IO) {
             repository.adduser(user)
         }
    }
}