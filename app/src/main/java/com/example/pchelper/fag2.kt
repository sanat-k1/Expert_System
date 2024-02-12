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
import com.example.pchelper.cpu_adapter1
import com.example.pchelper.gpu_adapter
import com.example.pchelper.gpu_re

class fag2 : Fragment() {
    private lateinit var cpu : RecyclerView
    private lateinit var gpu : RecyclerView
    private lateinit var cpu1sh : RecyclerView
    private lateinit var dbHelper: dbController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fag2, container, false)

        cpu = view.findViewById(R.id.cpu)
        gpu = view.findViewById(R.id.gpu)
        cpu1sh = view.findViewById(R.id.cpu2)
        val cpu1 = CPU_re(R.drawable.i12600k, "CPU Description 1")
        val cpu2 = CPU_re(R.drawable.i13600k, "CPU Description 2")
        val cpu3 = CPU_re(R.drawable.i12100,"cpu descriprion")
        val cpu4 = CPU_re(R.drawable.i13700k,"cpu desceehavghgshd")


        val item = ArrayList<CPU_re> ()
        val bkadapter = cpu_adapter(item)
        cpu.adapter = bkadapter
        cpu.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
// Create an instance of your data list
        val dataList = ArrayList<CPU_re>()
        item.add(cpu1)
        item.add(cpu2)
        item.add(cpu3)
        item.add(cpu4)
        val c = CPU_re(R.drawable.r7600x,"cpu desc")
        val c2 = CPU_re(R.drawable.r7600,"sdadsda")
        val c1 = CPU_re(R.drawable.r5500,"dsdadasda")
        val c4 = CPU_re(R.drawable.r3600,"sdsaadada")

        dataList.add(c)
        dataList.add(c2)
        dataList.add(c1)
        dataList.add(c4)

        val cuadapter = cpu_adapter1(dataList)

      cpu1sh.adapter   = cuadapter
        cpu1sh.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)


        val data = ArrayList<gpu_re>()
        item.add(cpu1)
        item.add(cpu2)
        item.add(cpu3)
        item.add(cpu4)
        val g = gpu_re(R.drawable.n3050,"cpu desc")
        val g2 = gpu_re(R.drawable.n1650,"sdadsda")
        val g1 = gpu_re(R.drawable.n3060,"dsdadasda")
        val g4 = gpu_re(R.drawable.n3080,"sdsaadada")

        data.add(g)
        data.add(g2)
        data.add(g1)
        data.add(g4)

        val gpadapter = gpu_adapter(data)

        gpu.adapter   = gpadapter
        gpu.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        return view

    }
}
