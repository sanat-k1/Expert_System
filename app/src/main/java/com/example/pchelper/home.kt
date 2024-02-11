package com.example.pchelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pchelper.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class home : AppCompatActivity() {
    lateinit var bottomNavigationMenuView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationMenuView = findViewById(R.id.bottomNavigationView)
        bottomNavigationMenuView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.Chatbot -> replaceFragment(chatbot())
                R.id.vish1 -> replaceFragment(bobthebuilder())
                R.id.vish2 -> replaceFragment(fag2())
                else ->{

                }
            }
            true
        }

        replaceFragment(chatbot())
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.Retardedstudio,fragment)
        fragmentTransaction.commit()
    }
}