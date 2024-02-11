package com.example.pchelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pchelper.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class home : AppCompatActivity() {
    lateinit var bottomNavigationMenuView: BottomNavigationView
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationMenuView = findViewById(R.id.bottomNavigationView)
        bottomNavigationMenuView.setOnClickListener {
            when(it.id)
            {
                R.id.Chatbot -> replaceFragment(chatbot())
                R.id.vish1 -> replaceFragment(fag1())
                R.id.vish2 -> replaceFragment(fag2())
            }

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