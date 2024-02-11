package com.example.pchelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dbController


class Yopc : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private lateinit var dbController: dbController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_yopc, container, false)
        // Initialize the database controller
        dbController = dbController(requireContext())
        val sewage = activity?.intent?.extras
        // Extracting data from the intent
        val usage = sewage?.getString("usage")
        val budget = sewage?.getInt("budget")
        val cpuType = sewage?.getString("cpuType")
        val gpuType = sewage?.getString("gpuType")
        val ssdCapacity = sewage?.getString("ssdCapacity")
        val ramCapacity = sewage?.getString("ramCapacity")
        // Assuming you have TextViews in your layout to display the PC configuration details
        val usageTextView = view.findViewById<TextView>(R.id.usageTextView)
        val budgetTextView = view.findViewById<TextView>(R.id.budgetTextView)
        val cpuTextView = view.findViewById<TextView>(R.id.cpuTextView)
        val gpuTextView = view.findViewById<TextView>(R.id.gpuTextView)
        val ssdTextView = view.findViewById<TextView>(R.id.ssdTextView)
        val ramTextView = view.findViewById<TextView>(R.id.ramTextView)

// Display the received data in the TextViews
        usageTextView.text = "Usage: $usage"
        budgetTextView.text = "Budget: $budget INR"
        cpuTextView.text = "CPU: $cpuType"
        gpuTextView.text = "GPU: $gpuType"
        ssdTextView.text = "SSD Capacity: $ssdCapacity"
        ramTextView.text = "RAM Capacity: $ramCapacity"

        // Inflate the layout for this fragment
        return view
    }
}