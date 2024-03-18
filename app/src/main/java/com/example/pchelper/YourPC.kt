package com.example.pchelper

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nothing.ketchum.Common
import com.nothing.ketchum.GlyphException
import com.nothing.ketchum.GlyphFrame
import com.nothing.ketchum.GlyphManager
import com.nothing.ketchum.GlyphManager.Callback
import dbController

class YourPC : AppCompatActivity() {

    var phone : Boolean = false

    private lateinit var mgm : GlyphManager
    private lateinit var callback: GlyphManager.Callback
    private lateinit var dbController: dbController
    lateinit var progressBar: ProgressBar
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
        val cpuType = intent.getStringExtra("cpuType") ?: "any"
        val gpuType = intent.getStringExtra("gpuType") ?: "any"
        val ssdCapacity = intent.getStringExtra("ssdCapacity")
        val ramCapacity = intent.getStringExtra("ramCapacity")
        // Assuming you have TextViews in your layout to display the PC configuration details
        val usageTextView = findViewById<TextView>(R.id.usageTextView)
        back = findViewById(R.id.floatingActionButton)

// Display the received data in the TextViews

        usageTextView.text = "Usage: $usage"

        back.setOnClickListener {
            val intent = Intent(this, home::class.java)
            intent.putExtra("mode", "yopc")
            startActivity(intent)
        }
        startgly()
        mgm = GlyphManager.getInstance(this)
        mgm.init(callback)

        var builder : GlyphFrame.Builder
        var frame: GlyphFrame

        var handler = Handler()
        progressBar = findViewById(R.id.pb)
        var i = progressBar.progress
        btn = findViewById(R.id.get_info)
        btn.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            Thread(Runnable {
                while (i<100)
                {
                    i++
                    handler.post(Runnable { progressBar!!.progress = i })
                    Thread.sleep(50)
                    if(phone) {
                        builder = mgm.glyphFrameBuilder
                        frame = builder.buildChannelD().build()
                        mgm.displayProgress(frame, i)
                    }
                }
                runOnUiThread {
                    if(phone) {
                        mgm.turnOff()
                    }
                    showpc(budget, usage, cpuType, gpuType, ramCapacity, ssdCapacity)
                    progressBar.visibility = View.GONE
                }
            }).start()
        }
    }

    private fun startgly() {
        callback = object :GlyphManager.Callback{
            override fun onServiceConnected(p0: ComponentName?) {
                if (Common.is20111()) mgm.register(Common.DEVICE_20111)
                if (Common.is22111()) mgm.register(Common.DEVICE_22111)
                if (Common.is23111()) mgm.register(Common.DEVICE_23111)
                try {
                    mgm.openSession()
                    phone = true
                } catch (e: GlyphException) {
                    Log.e("aaa", e.message!!)
                }
                Toast.makeText(applicationContext, "connected?", Toast.LENGTH_SHORT).show()
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                mgm.closeSession()
            }
        }
    }
    override fun onDestroy() {
        try {
            mgm.closeSession()
        }
        finally {
            Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
        }
        mgm.unInit()
        super.onDestroy()
    }

    private fun showpc(budget: Int, usage: String?, cpuType: String, gpuType: String, ramCapacity: String?, ssdCapacity: String?) {
        val ssdcap = findViewById<TextView>(R.id.ssdcapacity)
        val ssdprice = findViewById<TextView>(R.id.ssdprice)
        val ramcap = findViewById<TextView>(R.id.ramcapacity)
        val ramprice = findViewById<TextView>(R.id.ramprice)
        val tv= findViewById<TextView>(R.id.textView2)
        val cpu = findViewById<TextView>(R.id.textView5)
        val gpu = findViewById<TextView>(R.id.textView6)
        val ssd = findViewById<TextView>(R.id.textView7)
        val ram = findViewById<TextView>(R.id.textView8)
        tv.setVisibility(View.VISIBLE)
        cpu.setVisibility(View.VISIBLE)
        gpu.setVisibility(View.VISIBLE)
        ssd.setVisibility(View.VISIBLE)
        ram.setVisibility(View.VISIBLE)
        val ssdPrice = ssdPriceMap[ssdCapacity]
        var budg = 0
        var cpuPrice = 0
        var gpuPrice = 0

        if (ssdPrice != null) {
            // Store the SSD price in a variable
            val ssdPriceVariable = ssdPrice
            budg=budget-ssdPrice
            ssdprice.text= "Price: Rs."+ssdPriceVariable.toString()
            ssdcap.text= "Capacity: "+ssdCapacity
        } else {
            // Handle the case where the price is not found for the capacity
            Toast.makeText(this, "Price not found for SSD capacity: $ssdCapacity", Toast.LENGTH_SHORT).show()
        }
        // Get the RAM price based on the capacity
        val ramPrice = ramPriceMap[ramCapacity]
        if (ramPrice != null) {
            // Store the RAM price in a variable
            val ramPriceVariable = ramPrice
            budg-=ramPrice
            ramprice.text= "Price: Rs."+ramPriceVariable.toString()
            ramcap.text= "Capacity: "+ramCapacity
        } else {
            // Handle the case where the price is not found for the RAM capacity
            Toast.makeText(this, "Price not found for RAM capacity: $ramCapacity", Toast.LENGTH_SHORT).show()
        }
        var gprice = 0
        var cprice = 0
        if (usage=="home"){
            cprice = budg
            gprice = 0
        }
        else if (usage == "work"){
            cprice = (budg * 0.8).toInt()
            gprice = budg-cprice
        }
        else if( usage == "gaming"){
            cprice = (budg * 0.5).toInt()
            gprice = budg-cprice
        }
        else{
            cprice = (budg * 0.4).toInt()
            gprice = budg-cprice
        }

        val gpuInfo = dbController.get_gpuInfo(gprice, gpuType.toString())

        if (gpuInfo != null) {
            val (gpuName, gpuprice) = gpuInfo
            gpuPrice = gpuprice.toInt()
            val name = findViewById<TextView>(R.id.gpuname)
            name.text = gpuName
            val gpupricetv = findViewById<TextView>(R.id.gpuprice)
            gpupricetv.text = "Price : Rs." +gpuprice
        } else {
            // Handle case when GPU is not found
            val tv5 = findViewById<TextView>(R.id.gpuname)
            tv5.text = "No GPU found."
            val gpuprice = findViewById<TextView>(R.id.gpuprice)
            gpuprice.text = ""
        }

        val gpuInfo2 = dbController.get_gpuInfo2(gprice.toInt(), gpuType.toString())
        if (gpuInfo2 != null) {
            val (gpuVram, gpuClock, gpuimg) = gpuInfo2
            val vram = findViewById<TextView>(R.id.gpuvram)
            vram.text = "Vram : "+gpuVram.toString()+"GB"
            val clock = findViewById<TextView>(R.id.gpuclock)
            clock.text = "Clock : "+gpuClock.toString()+"GHz"
            val img = findViewById<ImageView>(R.id.gpuimg)
//                 Construct the resource identifier dynamically
            val resourceId = resources.getIdentifier(gpuimg, "drawable", packageName)
            // Set the image resource using the constructed identifier
            img.setImageResource(resourceId)
        } else {
            // Handle case when GPU is not found
            val vram = findViewById<TextView>(R.id.gpuvram)
            vram.text = "not found"
            val clock = findViewById<TextView>(R.id.gpuclock)
            clock.text = "not found"
        }

        val cpuinfo = dbController.get_cpuInfo(cprice.toInt(), cpuType.toString())
        if (cpuinfo != null) {
            val (cpuname, cpuprice, cpuimg) = cpuinfo
            cpuPrice = cpuprice.toInt()
            val name = findViewById<TextView>(R.id.cpuname)
            name.text = cpuname.toString()
            val price = findViewById<TextView>(R.id.cpuprice)
            price.text = "Price : Rs." +cpuprice.toString()
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

        val cpuInfo2 = dbController.get_cpuInfo2(cprice.toInt(), cpuType.toString())
        if (cpuInfo2 != null) {
            val (cpucore, cpuclock, cpuprice) = cpuInfo2
            val core = findViewById<TextView>(R.id.cpucore)
            core.text = "Cores: "+cpucore.toString()
            val clock = findViewById<TextView>(R.id.cpuclock)
            clock.text = "Clock : "+cpuclock.toString()+"GHz" }
        else {
            // Handle case when GPU is not found
            val core = findViewById<TextView>(R.id.cpucore)
            core.text = "not found"
            val clock = findViewById<TextView>(R.id.cpuclock)
            clock.text = "not found"
        }
        val totalPrice = ssdPrice!! + ramPrice!! + cpuPrice + gpuPrice
        val totalPricetv = findViewById<TextView>(R.id.totalprice)
        totalPricetv.text = "Total cost: Rs "+totalPrice.toString()+"/-"
    }

}
