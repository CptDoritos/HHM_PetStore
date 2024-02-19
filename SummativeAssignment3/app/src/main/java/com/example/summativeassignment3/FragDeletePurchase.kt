package com.example.summativeassignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragDeletePurchase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDeletePurchase : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_delete_purchase, container, false)

        val dm = DataManager(requireActivity())
        val editDeletePurchasePetId = view.findViewById(R.id.editDeletePurchasePetId) as EditText
        val editDeletePurchaseClientId = view.findViewById(R.id.editDeletePurchaseClientId) as EditText
        val btnDeletePurchase = view.findViewById(R.id.btnDeletePurchase) as Button

        updateList(view)

        btnDeletePurchase.setOnClickListener {
            val a = dm.searchPurchasePetId(editDeletePurchasePetId.text.toString().toInt())
            if(a.count > 0){
                val b = dm.searchPurchaseClientId(editDeletePurchaseClientId.text.toString().toInt())
                if(b.count > 0){
                    dm.deletePurchase(editDeletePurchasePetId.text.toString().toInt(), editDeletePurchaseClientId.text.toString().toInt())
                    Toast.makeText(activity, "Data from ${editDeletePurchasePetId.text} ${editDeletePurchaseClientId.text} Deleted", Toast.LENGTH_LONG).show()
                    editDeletePurchasePetId.text = null
                    editDeletePurchaseClientId.text = null
                    updateList(view)
                }else{
                    Toast.makeText(activity, "Invalid Client Id: ${editDeletePurchaseClientId.text}", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity, "Invalid Pet Id: ${editDeletePurchasePetId.text}", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterDeletePurchase = view.findViewById(R.id.txtDispDeletePurchase) as TextView

        val c = dm.selectAllPurchases()
        var list = ""

        while(c.moveToNext()){
            list += "Id: " + c.getString(0) + "\n" + "Pet Id: " + c.getString(1) + "\n" + "Client Id: " + c.getString(2) + "\n" + "Pet Name: " +c.getString(3) + "\n" + "Client First Name: " + c.getString(4) + "\n" + "Last Name: " + c.getString(5) + "\n------------------------------------------------------------\n \n"
        }

        textAfterDeletePurchase.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDeletePurchase.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDeletePurchase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}