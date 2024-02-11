package com.example.pchelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Builder.newInstance] factory method to
 * create an instance of this fragment.
 */
class Builder : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_builder, container, false)
    }
}