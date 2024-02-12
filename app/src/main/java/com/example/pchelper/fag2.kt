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
import androidx.recyclerview.widget.RecyclerView
import com.example.pchelper.R

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
    }
}
