package com.example.pchelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dbController

class YourPC : AppCompatActivity() {

    private lateinit var dbController: dbController
    lateinit var back: FloatingActionButton
    lateinit var btn: Button
    private val ssdPriceMap = mapOf(
        "256GB" to 1500,
        "512GB" to 3000,
        "1024GB" to 6000,
        "2048GB" to 12000,
        "4096GB" to 20000
    )
    private val ramPriceMap = mapOf(
        "8GB" to 2000,
        "16GB" to 4000,
        "32GB" to 8000,
        "64GB" to 16000,
        "128GB" to 32000
    )
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
            val intent = Intent(this, home::class.java)
            intent.putExtra("mode", "yopc")
            startActivity(intent)
        }
        btn = findViewById(R.id.get_info)
        btn.setOnClickListener {
            val ssdprice = findViewById<TextView>(R.id.ssdprice)
            val ramprice = findViewById<TextView>(R.id.ramprice)
            val ssdPrice = ssdPriceMap[ssdCapacity]
            if (ssdPrice != null) {
                // Store the SSD price in a variable
                val ssdPriceVariable = ssdPrice
                ssdprice.text= ssdPriceVariable.toString()
            } else {
                // Handle the case where the price is not found for the capacity
                Toast.makeText(this, "Price not found for SSD capacity: $ssdCapacity", Toast.LENGTH_SHORT).show()
            }
            // Get the RAM price based on the capacity
            val ramPrice = ramPriceMap[ramCapacity]
            if (ramPrice != null) {
                // Store the RAM price in a variable
                val ramPriceVariable = ramPrice
                ramprice.text= ramPriceVariable.toString()
                // Now you can use ramPriceVariable in other parts of your code
                // For example, you can pass it to another function or display it in a TextView
            } else {
                // Handle the case where the price is not found for the RAM capacity
                Toast.makeText(this, "Price not found for RAM capacity: $ramCapacity", Toast.LENGTH_SHORT).show()
            }
            val gpuInfo = dbController.get_gpuInfo(budget, gpuType.toString())

            if (gpuInfo != null) {
                val (gpuName, gpuPrice) = gpuInfo
                val name = findViewById<TextView>(R.id.gpuname)
                name.text = gpuName
                val gpuprice = findViewById<TextView>(R.id.gpuprice)
                gpuprice.text = gpuPrice
            } else {
                // Handle case when GPU is not found
                val tv5 = findViewById<TextView>(R.id.gpuname)
                tv5.text = "No GPU found."
                val gpuprice = findViewById<TextView>(R.id.gpuprice)
                gpuprice.text = ""
            }

            val gpuInfo2 = dbController.get_gpuInfo2(budget.toInt(), gpuType.toString())
            if (gpuInfo2 != null) {
                val (gpuVram, gpuClock, gpuTier) = gpuInfo2
                val vram = findViewById<TextView>(R.id.gpuvram)
                vram.text = gpuVram.toString()
                val clock = findViewById<TextView>(R.id.gpuclock)
                clock.text = gpuClock.toString()
            } else {
                // Handle case when GPU is not found
                val vram = findViewById<TextView>(R.id.gpuvram)
                vram.text = "not found"
                val clock = findViewById<TextView>(R.id.gpuclock)
                clock.text = "not found"
            }

            val cpuinfo = dbController.get_cpuInfo(budget.toInt(), cpuType.toString())
            if (cpuinfo != null) {
                val (cpuname, cpuprice, cpuimg) = cpuinfo
                val name = findViewById<TextView>(R.id.cpuname)
                name.text = cpuname.toString()
                val price = findViewById<TextView>(R.id.cpuprice)
                price.text = cpuprice.toString()
                val img = findViewById<ImageView>(R.id.cpuimg)
                // Construct the resource identifier dynamically
                val resourceId = resources.getIdentifier(cpuimg, "drawable", packageName)
                // Set the image resource using the constructed identifier
                img.setImageResource(resourceId)
            } else {
                val name = findViewById<TextView>(R.id.cpuname)
                name.text = "not found"
                val price = findViewById<TextView>(R.id.cpuprice)
                price.text = "not found"

            }


        }
        val cpuInfo2 = dbController.get_cpuInfo2(budget.toInt(), cpuType.toString())
        if (cpuInfo2 != null) {
           val (cpucore, cpuclock, cpuprice) = cpuInfo2
            val core = findViewById<TextView>(R.id.cpucore)
            core.text = cpucore.toString()
            val clock = findViewById<TextView>(R.id.cpuclock)
            clock.text = cpuclock.toString() }
        else {
          // Handle case when GPU is not found
           val core = findViewById<TextView>(R.id.cpucore)
           core.text = "not found"
            val clock = findViewById<TextView>(R.id.cpuclock)
              clock.text = "not found"
    }
        }

}