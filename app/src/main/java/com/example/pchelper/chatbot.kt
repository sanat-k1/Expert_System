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
                textView.text = "Bot: $botResponse"

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

}