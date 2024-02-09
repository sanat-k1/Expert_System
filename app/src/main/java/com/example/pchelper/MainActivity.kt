package com.example.pchelper

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist : ArrayList<QA>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var questions = arrayOf("GPU","CPU","RAM")
        datalist = ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        var qbar : AutoCompleteTextView = findViewById(R.id.editText)
        var qadapter: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.select_dialog_item,questions)
        qbar.threshold = 1
        qbar.setAdapter(qadapter)

        var db = getSharedPreferences("database",Context.MODE_PRIVATE)
        var editor = db.edit()



//       checking if database exist
        if(db.getString("database","null") == "null" )
        {
            editor.putString("database","db present")
        }

        recyclerView.layoutManager = LinearLayoutManager(this) // Set LayoutManager
        val data = QA("hello")
        datalist.add(data)
        recyclerView.adapter = QAAdapter(datalist)
    }
}