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
 * Use the [FragUpdateClient.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragUpdateClient : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_update_client, container, false)

        val editIdClient = view.findViewById(R.id.editUpdateIDClient) as EditText
        val btnUpdateClient = view.findViewById(R.id.btnUpdateClient) as Button
        val editfirstNameClient = view.findViewById(R.id.editUpdateClientfirstName) as EditText
        val editlastNameClient = view.findViewById(R.id.editUpdateClientlastName) as EditText
        val editPaymentMethod = view.findViewById(R.id.editUpdateClientpaymentMethod) as EditText

        val dm = DataManager(requireActivity())
        updateList(view)

        btnUpdateClient.setOnClickListener {
            var tid = 0
            if(btnUpdateClient.text == "Edit Client"){
                tid = (editIdClient.text.toString().toInt())
                val d = dm.searchIdClient(editIdClient.text.toString().toInt())

                if(d.count > 0){
                    d.moveToNext()
                    btnUpdateClient.text = "Update Client"

                    editfirstNameClient.setText("${d.getString(1)}", TextView.BufferType.EDITABLE)
                    editlastNameClient.setText("${d.getString(2)}", TextView.BufferType.EDITABLE)
                    editPaymentMethod.setText("${d.getString(3)}", TextView.BufferType.EDITABLE)
                }else{
                    Toast.makeText(activity, "Invalid Id Error, enter a different Id", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(activity, "Data Ready for Edit", Toast.LENGTH_LONG).show()
            }else{
                tid = editIdClient.text.toString().toInt()
                val tfName = editfirstNameClient.text.toString()
                val tlName = editlastNameClient.text.toString()
                val tPaymentMethod = editPaymentMethod.text.toString()
                dm.updateClient(tid, tfName, tlName, tPaymentMethod)

                btnUpdateClient.text = "Edit Client"
                Toast.makeText(activity, "Data edited to ${editfirstNameClient.text} ${editlastNameClient.text} ${editPaymentMethod.text}", Toast.LENGTH_LONG).show()

                updateList(view)
            }
        }

        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterUpdateClient = view.findViewById(R.id.txtDispUpdateClient) as TextView

        val d = dm.selectAllClients()
        var list = ""

        while(d.moveToNext()){
            list += "Id: " + d.getString(0) + "\n" + "First Name: " + d.getString(1) + "\n" + "Last Name: " + d.getString(2) + "\n" + "Payment Method: " + d.getString(3) + "\n------------------------------------------------------------\n \n"
        }

        textAfterUpdateClient.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragUpdateClient.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragUpdateClient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}