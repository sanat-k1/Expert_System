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
        val questions = arrayOf(
            "How to speed up my computer?",
            "What are the common causes of computer overheating?",
            "How to fix blue screen errors?",
            "What should I do if my computer won't turn on?",
            "How to troubleshoot frequent crashes?",
            "Why does my computer freeze frequently?",
            "How to improve internet speed?",
            "What are the signs of a failing hard drive?",
            "How to troubleshoot boot issues?",
            "How to free up memory on my computer?",
            "Why do I see a black screen on startup?",
            "How to prevent computer from overheating?",
            "Why does my PC shut down randomly?",
            "How to troubleshoot internet connection problems?",
            "Why is my computer not recognizing peripherals?",
            "How to optimize startup programs?",
            "How to prevent computer from running loud?",
            "How to remove adware from my computer?",
            "How to troubleshoot Windows boot issues?",
            "How to update graphics drivers?",
            "What to do if my computer is not responding?",
            "Why should I upgrade to a solid-state drive?",
            "How to check for malware infections?",
            "What are the symptoms of failing hardware?",
            "How to improve gaming performance?"
        )

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
                answer = getResponse(userInput)
            }

            msgs.savedata(question, answer)
            data= QA(question,answer)
            datalist.add(data)
            recyclerView.adapter = QAAdapter(datalist)
            qbar.text.clear()
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
            "Your computer might be overheating. Check if the fans are working properly and clean any dust buildup.",
            "If your PC is slow, try freeing up disk space by deleting unnecessary files and programs.",
            "Blue screen errors often indicate hardware or driver issues. Update your drivers and run hardware diagnostics.",
            "If your computer is not turning on, check the power supply and ensure all connections are secure.",
            "Frequent crashes could be caused by software conflicts or faulty hardware. Check for software updates and run diagnostics.",
            "If your PC is freezing frequently, check for malware infections and consider upgrading your RAM.",
            "Slow internet speed may be due to network congestion or issues with your ISP. Try resetting your modem/router.",
            "If your computer is making strange noises, it could be a failing hard drive or fan. Backup important data immediately.",
            "If your PC won't boot, try booting into safe mode and running system repair tools.",
            "Error messages like 'disk not found' may indicate a failing hard drive. Backup data and consider replacing the drive.",
            "If your computer is running out of memory, consider upgrading your RAM or closing unnecessary programs.",
            "Black screen on startup may be due to a faulty graphics card or corrupt system files. Try booting into safe mode.",
            "If your computer is running hot, check for blocked vents and consider improving airflow with additional fans.",
            "If your PC shuts down randomly, it could be due to overheating or a failing power supply. Check internal components.",
            "Intermittent internet connection issues may be caused by router/modem problems. Reset the devices and check cables.",
            "If your computer is not recognizing peripherals, check device connections and drivers. Try using different ports.",
            "If your PC is running slowly, try disabling startup programs and optimizing system performance settings.",
            "If your computer is overheating, check for dust buildup in vents and consider reapplying thermal paste to the CPU.",
            "Error messages like 'DLL not found' may indicate missing or corrupt system files. Try reinstalling affected software.",
            "If your computer is running loud, check for fan obstructions and consider replacing noisy components.",
            "Frequent pop-up ads may indicate adware infections. Run antivirus scans and remove any detected threats.",
            "If your PC is not booting into Windows, try using the Windows Recovery Environment to troubleshoot startup issues.",
            "If your computer is crashing during gaming, update your graphics drivers and check for overheating.",
            "If your PC is not responding, try force restarting by holding down the power button for several seconds.",
            "If your computer is running slowly, consider upgrading to a solid-state drive (SSD) for improved performance."
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