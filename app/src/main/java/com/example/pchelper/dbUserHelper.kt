package com.example.pchelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbUserHelper(context: Context):SQLiteOpenHelper(context, "userdata", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table userdata (username TEXT primary key, password Text, email TEXT, mobile INT )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("drop table if exists userdata")
    }

    fun insert_data(username: String, passsword: String, email: String, mobile: String): Boolean{

        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", passsword)
        cv.put("email", email)
        cv.put("mobile", mobile)

        val result = p0.insert("userdata",null,cv)
        return result != (-1).toLong()
    }

    fun checkuserpass(email: String, passsword: String):Boolean{
        val p0 = this.writableDatabase
        val query = "select * from userdata where email ='$email' and password = '$passsword'"
        val cursor = p0.rawQuery(query, null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}