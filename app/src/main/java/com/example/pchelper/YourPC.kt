package com.example.pchelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dbController

class YourPC : AppCompatActivity() {

    private lateinit var dbController: dbController
    lateinit var back: FloatingActionButton
    lateinit var btn: Button
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
        // Assuming you have TextViews in your layout to display the PC configuration details
        val usageTextView = findViewById<TextView>(R.id.usageTextView)
        val budgetTextView = findViewById<TextView>(R.id.budgetTextView)
        val cpuTextView = findViewById<TextView>(R.id.cpuTextView)
        val gpuTextView = findViewById<TextView>(R.id.gpuTextView)
        val ssdTextView = findViewById<TextView>(R.id.ssdTextView)
        val ramTextView = findViewById<TextView>(R.id.ramTextView)
        back = findViewById(R.id.floatingActionButton)

// Display the received data in the TextViews

        usageTextView.text = "Usage: $usage"
        budgetTextView.text = "Budget: $budget INR"
        cpuTextView.text = "CPU: $cpuType"
        gpuTextView.text = "GPU: $gpuType"
        ssdTextView.text = "SSD Capacity: $ssdCapacity"
        ramTextView.text = "RAM Capacity: $ramCapacity"

        back.setOnClickListener {
        val intent = Intent(this,home::class.java)
        intent.putExtra("mode","yopc")
        startActivity(intent)


        }
        btn= findViewById(R.id.button2)
        btn.setOnClickListener {
            val gpuInfo = dbController.get_gpuInfo(budget.toInt(), gpuType.toString())
            val gpuInfo2= dbController.get_gpuInfo2(budget.toInt(), gpuType.toString())
            if (gpuInfo != null) {
                val (gpuName, gpuPrice) = gpuInfo

                val tv5 = findViewById<TextView>(R.id.textView5)
                tv5.text = gpuName

                val gpuprice = findViewById<TextView>(R.id.gpuprice)
                gpuprice.text = gpuPrice
            }
            if (gpuInfo2 !=null){

                val (gpuVram, gpuClock, gpuTier) = gpuInfo2

                val gpuvram=findViewById<TextView>(R.id.Vram)
                gpuvram.text = gpuVram.toString()

                val gpuclock=findViewById<TextView>(R.id.clock)
                gpuclock.text = gpuClock.toString()

                val gputier=findViewById<TextView>(R.id.tier)
                gputier.text = gpuTier.toString()


            }
        }


    }
}