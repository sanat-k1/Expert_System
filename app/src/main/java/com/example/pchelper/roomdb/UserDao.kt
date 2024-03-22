package com.example.pchelper.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addqa(user: user)

    @Query("Select * FROM chatbot")
    fun readdata():LiveData<List<user>>

}