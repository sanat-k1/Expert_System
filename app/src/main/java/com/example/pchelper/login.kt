package com.example.pchelper

import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var login_tv: TextView
    private lateinit var db: dbUserHelper
    lateinit var btn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email= findViewById(R.id.login_email)
        pass = findViewById(R.id.login_password)
        login_tv =findViewById(R.id.login_tv)
        btn = findViewById(R.id.login_submit)
        db= dbUserHelper(this)
        val sharedpref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        var edit = sharedpref.edit()

        login_tv.setOnClickListener{
            var intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener {
            val mail = email.text.toString()
            val pword = pass.text.toString()

            if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pword))
            {
                Toast.makeText(this, "Please fill the Required Fields", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkuser = db.checkuserpass(mail,pword)
                if (checkuser){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Add preferences
                    edit.putString("email", email.text.toString())
                    edit.putString("password", pass.text.toString())
                    edit.apply()

                    // Create an intent to start MainActivity
                    val mainIntent = Intent(this, MainActivity::class.java)

                    // Create an intent to start HomeActivity
                    val homeIntent = Intent(this, home::class.java)

                    // Create a task stack containing both activities
                    val stackBuilder = TaskStackBuilder.create(this)
                        .addNextIntent(mainIntent)
                        .addNextIntent(homeIntent)

                    // Start the task stack
                    stackBuilder.startActivities()

                    // Finish the current activity
                    finish()
                }

                else{
                    Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}