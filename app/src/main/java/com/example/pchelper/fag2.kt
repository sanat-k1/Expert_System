import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pchelper.CPU_re
import com.example.pchelper.R
import com.example.pchelper.cpu_adapter

class fag2 : Fragment() {
    private lateinit var cpu : RecyclerView
    private lateinit var gpu : RecyclerView
    private lateinit var cpu1 : RecyclerView
    private lateinit var dbHelper: dbController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fag2, container, false)

        cpu = view.findViewById(R.id.cpu)
        gpu = view.findViewById(R.id.gpu)
        cpu1 = view.findViewById(R.id.cpu2)
        return view

// Create an instance of your data list
        val dataList = ArrayList<CPU_re>()

// Populate dataList with your data objects (CPU_re objects)
// For example:
        val cpu1 = CPU_re(R.drawable.i12600k, "CPU Description 1")
        val cpu2 = CPU_re(R.drawable.i13600k, "CPU Description 2")
// Add more CPU_re objects as needed

        dataList.add(cpu1)
        dataList.add(cpu2)
// Add more data to dataList if needed

// Create an instance of the adapter with your data list
        val adapter = cpu_adapter(dataList)
        cpu.layoutManager = LinearLayoutManager(requireContext())

// Set the adapter to your RecyclerView
        cpu.adapter = adapter

    }
}
