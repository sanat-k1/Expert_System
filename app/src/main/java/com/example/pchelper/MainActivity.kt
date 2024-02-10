package com.example.pchelper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist : ArrayList<QA>
    private lateinit var qbar : AutoCompleteTextView
    private lateinit var jif : ImageView
    private lateinit var submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit= findViewById(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)
        qbar   = findViewById(R.id.editText)
        jif= findViewById(R.id.imageView)

        datalist = ArrayList()
        var questions = arrayOf("GPU","CPU","RAM")

        var qadapter: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.select_dialog_item,questions)
        qbar.threshold = 1
        qbar.setAdapter(qadapter)

        var db = getSharedPreferences("database",Context.MODE_PRIVATE)
        var editor = db.edit()

//        jifff
        Glide.with(this)
            .asGif()
            .load(R.drawable.giphy)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(jif)

//        making a gay button
        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.red),
            ContextCompat.getColor(this, R.color.green),
            ContextCompat.getColor(this, R.color.blue),
            ContextCompat.getColor(this, R.color.Cyan),
            ContextCompat.getColor(this, R.color.Magenta),
            ContextCompat.getColor(this, R.color.yellow)
        )

//        while (true) {
//            for (i in 0 until colors.size) {
//                val color = colors[i]
//                submit.setBackgroundColor(color)
//            }
//        }

//        val lgbtlights  = AnimationUtils.loadAnimation(this,R.anim.lgbt)
//        submit.startAnimation(lgbtlights)
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