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
 * Use the [FragDeleteClient.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDeleteClient : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_delete_client, container, false)

        val editDeleteClientfirstName = view.findViewById(R.id.editDeleteClientfirstName) as EditText
        val editDeleteClientlastName = view.findViewById(R.id.editDeleteClientlastName) as EditText
        val btnDeleteClient = view.findViewById(R.id.btnDeleteClient) as Button

        val dm = DataManager(requireActivity())
        updateList(view)

        btnDeleteClient.setOnClickListener {
            dm.deleteClient(editDeleteClientfirstName.text.toString(), editDeleteClientlastName.text.toString())
            Toast.makeText(activity, "Data from ${editDeleteClientfirstName.text} ${editDeleteClientlastName.text} deleted", Toast.LENGTH_LONG).show()
            editDeleteClientfirstName.text = null
            editDeleteClientlastName.text = null
            updateList(view)
        }

        updateList(view)
        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterDeleteClient = view.findViewById(R.id.txtDispDeleteClient) as TextView

        val d = dm.selectAllClients()
        var list = ""

        while(d.moveToNext()){
            list += "Id: " + d.getString(0) + "\n" + "First Name: " + d.getString(1) + "\n" + "Last Name: " + d.getString(2) + "\n" + "Payment Method: " + d.getString(3) + "\n------------------------------------------------------------\n \n"
        }

        textAfterDeleteClient.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDeleteClient.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDeleteClient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}