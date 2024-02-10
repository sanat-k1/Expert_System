package com.example.pchelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QAStore(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        const val DATABASE_VERSION =1
        const val DATABASE_NAME = "QASTORE"
        private const val Tablename = "QA"
         const val Question = "Question"
         const val Answer = "Answer"
        private const val CREATE_TABLE_QA = "CREATE TABLE $Tablename (" +
                "$Question TEXT,"+
                "$Answer TEXT"+
                ")"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_QA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $Tablename")
        onCreate(db)
    }


    fun savedata(question : String,answer : String){
        val writer = this.writableDatabase
        val cv = ContentValues()
        cv.put("Question",question)
        cv.put("Answer",answer)
        this.writableDatabase.insert("QASTORE",null,cv)
        writer.close()
    }

    fun getdata() : Cursor?{
        val writer = this.writableDatabase
        val cursor = writer.rawQuery("select * from $Tablename", null)
        return cursor
    }

}