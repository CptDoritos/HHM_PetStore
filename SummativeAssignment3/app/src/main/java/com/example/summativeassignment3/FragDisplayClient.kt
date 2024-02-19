package com.example.summativeassignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragDisplayClient.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDisplayClient : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_display_client, container, false)

        val btnSearchClients = view.findViewById(R.id.btnSearchClients) as Button
        val editSearchClientsfirstName = view.findViewById(R.id.editTextSearchClientsfirstName) as EditText
        val editSearchClientslastName = view.findViewById(R.id.editTextSearchClientslastName) as EditText
        val textResultsClients = view.findViewById(R.id.textResultsClients) as TextView
        val radioGroupClients = view.findViewById(R.id.rdioGroupDisplayClients) as RadioGroup
        val radioAllClients = view.findViewById(R.id.rdioAllClients) as RadioButton
        val radioSearchClients = view.findViewById(R.id.rdioSearchClients) as RadioButton
        val laySearchClients = view.findViewById(R.id.searchHolderClients) as LinearLayout

        val dm = DataManager(requireActivity())
        updateList(view)

        radioAllClients.setOnClickListener {
            if(radioAllClients.isChecked){
                laySearchClients.visibility = View.GONE
            }
            updateList(view)
        }
        radioSearchClients.setOnClickListener {
            if(radioSearchClients.isChecked){
                laySearchClients.visibility = View.VISIBLE
                textResultsClients.text = "Your Search Results for Clients Will be Displayed Here"
            }
        }
        btnSearchClients.setOnClickListener {
            val d = dm.searchNameClient(editSearchClientsfirstName.text.toString(), editSearchClientslastName.text.toString())
            if(d.count > 0){
                d.moveToNext()
                textResultsClients.text =
                    "Result = Id: ${d.getString(0)}\n First Name: ${d.getString(1)}\n Last Name: ${d.getString(2)}\n Payment Method: ${d.getString(3)}"
            }else{
                Toast.makeText(activity, "Unknown Client Searched", Toast.LENGTH_LONG).show()
            }
        }

        updateList(view)
        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textResultsClients = view.findViewById(R.id.textResultsClients) as TextView

        val d = dm.selectAllClients()
        var list = ""

        while(d.moveToNext()){
            list += "Id: " + d.getString(0) + "\n" + "First Name: " + d.getString(1) + "\n" + "Last Name: " + d.getString(2) + "\n" + "Payment Method: " + d.getString(3) + "\n------------------------------------------------------------\n \n"
        }

        textResultsClients.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDisplayClient.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDisplayClient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}