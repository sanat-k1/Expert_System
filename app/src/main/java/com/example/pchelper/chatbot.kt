package com.example.pchelper

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class chatbot : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var datalist : ArrayList<QA>
    private lateinit var qbar : AutoCompleteTextView
    private lateinit var jif : ImageView
    private lateinit var submit: Button
    private lateinit var msgs: QAStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chatbot, container, false)
        var question: String
        var answer : String

        submit= view.findViewById(R.id.button)
        recyclerView = view.findViewById(R.id.recyclerView)
        qbar   = view.findViewById(R.id.editText)
        jif= view.findViewById(R.id.imageView)

        datalist = ArrayList()
        var data = QA("hello","Hello")
        datalist.add(data)
        var questions = arrayOf("GPU","CPU","RAM")

        var qadapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),android.R.layout.select_dialog_item,questions)
        qbar.threshold = 1
        qbar.setAdapter(qadapter)

//        jifff
        Glide.with(this)
            .asGif()
            .load(R.drawable.giphy)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
            .into(jif)

//        making a gay button
        val colors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.red),
            ContextCompat.getColor(requireContext(), R.color.green),
            ContextCompat.getColor(requireContext(), R.color.blue),
            ContextCompat.getColor(requireContext(), R.color.Cyan),
            ContextCompat.getColor(requireContext(), R.color.Magenta),
            ContextCompat.getColor(requireContext(), R.color.yellow)
        )
        answer= "i dont know boss"
        submit.setOnClickListener {
            question=qbar.text.toString()
            val userInput = qbar.text.toString().trim()
            if(question.isNotBlank()){
                val botResponse = getResponse(userInput)

            }

            msgs.savedata(question, answer)
            data= QA(question,answer)
            datalist.add(data)
            recyclerView.adapter = QAAdapter(datalist)
        }


//  store and retrieve
        msgs= QAStore(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        displaymsg()

        recyclerView.adapter = QAAdapter(datalist)
        return view
    }

    private fun displaymsg() {
        var newcursor: Cursor? = msgs!!.getdata()
        var newaar = ArrayList<QA>()
        while (newcursor!!.moveToNext())
        {
            val q = newcursor.getString(0)
            val a = newcursor.getString(1)
            newaar.add(QA(q,a))
        }
        recyclerView.adapter = QAAdapter(newaar)
        // Inflate the layout for this fragment

    }
    private fun getResponse(userInput: String): String {
        val splitMessage = userInput.toLowerCase().split(Regex("\\s+|[,;?!.-]\\s*"))
        return checkAllMessages(splitMessage)
    }
    private fun checkAllMessages(message: List<String>): String {
        val responses = listOf(
            "I recommend taking your car to a professional mechanic for a thorough inspection.",
            "If your car is not starting, it might be a battery issue. Check the battery connection, charge, and fuel level before attempting to start again.",
            "Strange noises or warning lights may indicate an engine problem. Pay attention to any unusual sounds or dashboard warnings.",
            "To improve handling, check tire pressure and condition regularly. Proper tire maintenance can prevent handling issues.",
            "If your headlights are not turning on, it could be due to burned-out bulbs or power/ground issues. Inspect and fix accordingly.",
            "If your AC is not cooling or turning on, check refrigerant levels, clean the air filter, and inspect the compressor and condenser for issues.",
            "Worn-out brakes can lead to slow stopping and unusual noises. Consider changing brake pads if you notice vibrations or odd sounds while braking.",
            "If you notice a burning smell, it could be an overheating issue. Check the coolant levels and radiator for any leaks.",
            "A vibrating steering wheel may indicate an issue with the wheel balance or alignment. Consider getting a wheel alignment and balancing.",
            "A persistent check engine light could be signaling a problem with the vehicle's engine or emissions system. It's recommended to have the car's diagnostics checked.",
            "If your car pulls to one side while driving, it may be due to uneven tire wear or alignment issues. Check the tire tread and alignment.",
            "For a fuel-efficient drive, ensure your tires are properly inflated, and the air filter is clean. Regular maintenance can improve fuel efficiency.",
            "If you experience a loss of power, it could be related to issues with the fuel system or air intake. Check the fuel pump and air filter for any problems.",
            "Unusual smells from the exhaust could indicate problems with the catalytic converter or exhaust system. Inspect for any visible damage or leaks.",
            "Transmission problems may cause jerking or hesitation during gear shifts. It is advisable to have the transmission checked for issues."
        )

        val probabilities = responses.associateWith { response ->
            messageProbability(message, response.split(" ").toSet())
        }

        val bestMatch = probabilities.maxByOrNull { it.value }?.key

        return bestMatch ?: "I'm sorry, I don't have enough information to diagnose the issue."
    }

    private fun messageProbability(userMessage: List<String>, recognizedWords: Set<String>): Int {
        val messageCertainty = userMessage.count { it in recognizedWords }
        return (messageCertainty.toDouble() / recognizedWords.size * 100).toInt()
    }
}