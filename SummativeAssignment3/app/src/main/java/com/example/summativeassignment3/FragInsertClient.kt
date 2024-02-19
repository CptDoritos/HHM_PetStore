package com.example.summativeassignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragInsertClient.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragInsertClient : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_insert_client, container, false)

        val editClientfirstName = view.findViewById(R.id.editNameClientfirstName) as EditText
        val editClientlastName = view.findViewById(R.id.editNameClientlastName) as EditText
        val editClientpaymentMethod = view.findViewById(R.id.editNameClientpaymentMethod) as EditText
        val btnInsertClient = view.findViewById(R.id.btnInsertClient) as Button

        val dm = DataManager(requireActivity())

        btnInsertClient.setOnClickListener {
            var client1 = Client(editClientfirstName.text.toString(), editClientlastName.text.toString(), editClientpaymentMethod.text.toString())
            dm.insertClient(client1.firstName, client1.lastName, client1.paymentMethod)
            Toast.makeText(activity, "${client1.firstName} ${client1.lastName} ${client1.paymentMethod} Inserted", Toast.LENGTH_LONG).show()
            editClientfirstName.text = null
            editClientlastName.text = null
            editClientpaymentMethod.text = null

            //dm.resetTableClients()
            //Toast.makeText(context, "Table Reset!", Toast.LENGTH_LONG).show()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragInsertClient.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragInsertClient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}