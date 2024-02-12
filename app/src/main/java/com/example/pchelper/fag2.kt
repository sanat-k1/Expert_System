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
import com.example.pchelper.R

class fag2 : Fragment() {
    private lateinit var listViewItems: ListView
    private lateinit var dbHelper: dbController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fag2, container, false)
        listViewItems = view.findViewById(R.id.listViewItems)
        dbHelper = dbController(requireContext())
        displayProducts()
        return view
    }

    private fun displayProducts() {
        val cursor: Cursor? = dbHelper.getAllCPUs()
        val adapter = ProductCursorAdapter(requireContext(), cursor)
        listViewItems.adapter = adapter
    }

    private class ProductCursorAdapter(context: Context, cursor: Cursor?) : CursorAdapter(context, cursor, 0) {

        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            return LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false)
        }




        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val productName = cursor?.getString(cursor.getColumnIndex(dbController.CPU_NAME))
            val productPrice = cursor?.getDouble(cursor.getColumnIndex(dbController.CPU_PRICE))
            val productImageResId = context?.resources?.getIdentifier("drawable/${productName?.toLowerCase()}", null, context.packageName)

            view?.findViewById<ImageView>(R.id.componentImage)?.setImageResource(productImageResId ?: R.drawable.default_image)
            view?.findViewById<TextView>(R.id.componentName)?.text = productName
            view?.findViewById<TextView>(R.id.componentPrice)?.text = productPrice.toString()
        }
    }
}
