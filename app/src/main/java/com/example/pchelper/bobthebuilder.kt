package com.example.pchelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast


class bobthebuilder : Fragment() {
    private lateinit var usageSpinner: Spinner
    private lateinit var budgetSeekBar: SeekBar
    private lateinit var budgetValueTextView: TextView
    private lateinit var autoSelectComponentsCheckBox: CheckBox
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
    private lateinit var dataFetcher: DataFetcher
    // Variable to store the selected usage
    private var selectedCpu: String? = null
    private var selectedGpu: String? = null
    private var selectedRam: String? = null
    private var selectedSsd: String? = null

    var user_usage: String? = null
    var budget : Int =0
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
        dataFetcher = DataFetcher(requireContext())

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
                user_usage = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Show toast to choose usage when nothing is selected
                Toast.makeText(requireContext(), "Please choose a usage", Toast.LENGTH_SHORT).show()
            }
        }


        val maxBudget = 250000 // Maximum budget in rupees
        val stepSize = 10000 // Increment step size in rupees
        budgetSeekBar.max = maxBudget / stepSize // Set max progress based on step size

        // Set budget seekbar progress change listener
        budgetSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Calculate budget value based on progress and step size
                budget = progress * stepSize
                // Update budget value text view
                budgetValueTextView.text = "$budget INR"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
        })

        intelRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedCpu = "Intel"
        }

        amdRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedCpu = "AMD"
        }



        // Inflate the layout for this fragment
        return view
    }

}