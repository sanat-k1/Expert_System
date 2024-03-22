package com.example.pchelper.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatbot")
data class user (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val question : String,
            val answer : String
)