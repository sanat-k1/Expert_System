package com.example.pchelper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import dbController
class bobthebuilder : Fragment() {
    private lateinit var usageSpinner: Spinner
    private lateinit var budgetSeekBar: SeekBar
    private lateinit var budgetValueTextView: TextView
    private lateinit var intelRadioButton: RadioButton
    private lateinit var amdRadioButton: RadioButton
    private lateinit var anyCpuRadioButton: RadioButton
    private lateinit var nvidiaRadioButton: RadioButton
    private lateinit var amdGpuRadioButton: RadioButton
    private lateinit var anyGpuRadioButton: RadioButton
    private lateinit var ssdCapacitySeekBar: SeekBar
    private lateinit var ssdCapacityValueTextView: TextView
    private lateinit var ramCapacitySeekBar: SeekBar
    private lateinit var ramCapacityValueTextView: TextView
    private lateinit var submitButton: Button
    // Variable to store the selected usage
    private var selectedCpu: String? = null
    private var selectedGpu: String? = null
    private var selectedRam: String? = null
    private var userUsage: String? = null
    private var selectedSsd: String? = null
    private var budget : Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bobthebuilder, container, false)
        usageSpinner = view.findViewById(R.id.usageSpinner)
        budgetSeekBar = view.findViewById(R.id.budgetSeekBar)
        budgetValueTextView = view.findViewById(R.id.budgetValueTextView)
        val dbController = dbController(requireContext())
        intelRadioButton = view.findViewById(R.id.intelRadioButton)
        amdRadioButton = view.findViewById(R.id.amdRadioButton)
        anyCpuRadioButton = view.findViewById(R.id.anyCpuRadioButton)
        nvidiaRadioButton = view.findViewById(R.id.nvidiaRadioButton)
        amdGpuRadioButton = view.findViewById(R.id.amdGpuRadioButton)
        anyGpuRadioButton = view.findViewById(R.id.anyGpuRadioButton)
        ssdCapacitySeekBar = view.findViewById(R.id.ssdCapacitySeekBar)
        ssdCapacityValueTextView = view.findViewById(R.id.ssdCapacityValueTextView)
        ramCapacitySeekBar = view.findViewById(R.id.ramCapacitySeekBar)
        ramCapacityValueTextView = view.findViewById(R.id.ramCapacityValueTextView)
        submitButton = view.findViewById(R.id.submit_button)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.usage_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            usageSpinner.adapter = adapter
        }
        usageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                userUsage = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Show toast to choose usage when nothing is selected
                Toast.makeText(requireContext(), "Please choose a usage", Toast.LENGTH_SHORT).show()
            }
        }
        val maxBudget = 250000 // Maximum budget in rupees
        val stepSize = 5000 // Increment step size in rupees

// Set max progress based on step size
        budgetSeekBar.max = maxBudget / stepSize

// Calculate the progress for the default value (50k)
        val defaultValue = 50000
        val defaultProgress = defaultValue / stepSize

// Set the initial value of budget to the default value (50k)
        var budget = defaultValue

// Set the progress of the budgetSeekBar to the default value
        budgetSeekBar.progress = defaultProgress

// Update budget value text view with the default value
        budgetValueTextView.text = "$defaultValue INR"

// Set budget seekbar progress change listener
        budgetSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate budget value based on progress and step size
                budget = progress * stepSize
                // Update budget value text view
                budgetValueTextView.text = "$budget INR"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        selectedCpu = "any"
        // Radio button listeners for CPU
        intelRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedCpu = "Intel"
        }
        amdRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedCpu = "AMD"
        }
        anyCpuRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedCpu = "any"
        }
        selectedGpu="any"
        // Radio button listeners for GPU
        nvidiaRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedGpu = "Nvidia"
        }
        amdGpuRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedGpu = "AMD"
        }
        anyGpuRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedGpu = "any"
        }

        // Set available SSD capacities and their corresponding progress values
        val ssdCapacities = listOf(256, 512, 1024, 2048, 4096)
        val maxSsdProgress = ssdCapacities.size - 1
        val ssdStepSize = 1

// Set max progress based on the number of capacities
        ssdCapacitySeekBar.max = maxSsdProgress

// Set initial progress and selected SSD capacity
        val defaultSsdIndex = 0
        val defaultSsdCapacity = "${ssdCapacities[defaultSsdIndex]}GB"
        ssdCapacitySeekBar.progress = defaultSsdIndex
        selectedSsd = defaultSsdCapacity

// Update SSD capacity text view with the default capacity
        ssdCapacityValueTextView.text = defaultSsdCapacity

// Set listener for SSD capacity seekbar
        ssdCapacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate SSD capacity based on progress index
                selectedSsd = "${ssdCapacities[progress]}GB"
                // Update SSD capacity text view
                ssdCapacityValueTextView.text = selectedSsd
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

// Set available RAM capacities and their corresponding progress values
        val ramCapacities = listOf(8, 16, 32, 64, 128)
        val maxRamProgress = ramCapacities.size - 1
        val ramStepSize = 1

// Set max progress based on the number of capacities
        ramCapacitySeekBar.max = maxRamProgress

// Set initial progress and selected RAM capacity
        val defaultRamIndex = 0
        val defaultRamCapacity = "${ramCapacities[defaultRamIndex]}GB"
        ramCapacitySeekBar.progress = defaultRamIndex
        selectedRam = defaultRamCapacity

// Update RAM capacity text view with the default capacity
        ramCapacityValueTextView.text = defaultRamCapacity

// Set listener for RAM capacity seekbar
        ramCapacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate RAM capacity based on progress index
                selectedRam = "${ramCapacities[progress]}GB"
                // Update RAM capacity text view
                ramCapacityValueTextView.text = selectedRam
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        submitButton.setOnClickListener {
            // Gather all the selected values
            val selectedUsage = userUsage
            val selectedBudget = budget
            val selectedCpuType = selectedCpu
            val selectedGpuType = selectedGpu
            val selectedSsdCapacity = selectedSsd
            val selectedRamCapacity = selectedRam
            val ssdPrice = dbController.showSsd("256")

            // Create an Intent to start the new activity
            val intent = Intent(requireContext(),YourPC::class.java)

            // Pass the gathered values to the intent as extras
            intent.putExtra("usage", selectedUsage)
            intent.putExtra("budget", selectedBudget)
            intent.putExtra("cpuType", selectedCpuType)
            intent.putExtra("gpuType", selectedGpuType)
            intent.putExtra("ssdCapacity", selectedSsdCapacity)
            intent.putExtra("ramCapacity", selectedRamCapacity)

//             Start the new activity
//             Start the new activity
            requireContext().startActivity(intent)

        }
        return view
    }

}