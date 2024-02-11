package com.example.pchelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dbController

class register : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var user_name: EditText
    lateinit var pass: EditText
    lateinit var mobile: EditText
    lateinit var somebtn: TextView
    lateinit var reg_submit: Button
    private lateinit var db: dbUserHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email=findViewById(R.id.email)
        user_name=findViewById(R.id.user_name)
        pass= findViewById(R.id.reg_password)
        mobile = findViewById(R.id.reg_mobile)
        reg_submit= findViewById(R.id.reg_submit)
        somebtn = findViewById(R.id.somebtn)
        db = dbUserHelper(this)

        somebtn.setOnClickListener{
            val intent = Intent(this, login::class.java)

            startActivity(intent)
        }

        reg_submit.setOnClickListener {
            val uname = user_name.text.toString()
            val pword = pass.text.toString()
            val mob = mobile.text.toString()
            val mail = email.text.toString()
            val savedata = db.insert_data(uname, pword, mail, mob)
            if (TextUtils.isEmpty(uname) ||TextUtils.isEmpty(pword) ||TextUtils.isEmpty(mob) ||TextUtils.isEmpty(mail) ){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                if (savedata==true){
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, login::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
 }