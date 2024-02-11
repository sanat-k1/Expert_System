package com.example.pchelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dbController

class YourPC : AppCompatActivity() {

    private lateinit var dbController: dbController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_pc)
        // Initialize the database controller
        dbController = dbController(this)
        // Extracting data from the intent
        val usage = intent.getStringExtra("usage")
        val budget = intent.getIntExtra("budget", 0)
        val cpuType = intent.getStringExtra("cpuType")
        val gpuType = intent.getStringExtra("gpuType")
        val ssdCapacity = intent.getStringExtra("ssdCapacity")
        val ramCapacity = intent.getStringExtra("ramCapacity")
    }
}