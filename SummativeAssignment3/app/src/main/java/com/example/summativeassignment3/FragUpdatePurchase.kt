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
 * Use the [FragUpdatePurchase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragUpdatePurchase : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_update_purchase, container, false)

        val dm = DataManager(requireActivity())

        val editIdPurchase = view.findViewById(R.id.editUpdateIdPurchase) as EditText
        val btnUpdatePurchase = view.findViewById(R.id.btnUpdatePurchase) as Button
        val editPurchasePetId = view.findViewById(R.id.editUpdatePurchasePetId) as EditText
        val editPurchaseClientId = view.findViewById(R.id.editUpdatePurhaseClientId) as EditText
        val editPurchasePetName = view.findViewById(R.id.editUpdatePurchasePetName) as EditText
        val editPurchaseClientFirstName = view.findViewById(R.id.editUpdatePurchaseClientFirstName) as EditText
        val editPurchaseClientLastName = view.findViewById(R.id.editUpdatePurchaseClientLastName) as EditText

        updateList(view)

        btnUpdatePurchase.setOnClickListener {
            var tid = 0
            if(btnUpdatePurchase.text == "Edit Purchase"){
                tid = (editIdPurchase.text.toString().toInt())
                val c = dm.searchIdPurchase(editIdPurchase.text.toString().toInt())

                if(c.count > 0){
                    c.moveToNext()
                    btnUpdatePurchase.text = "Update Purchase"

                    editPurchasePetId.setText("${c.getString(1)}", TextView.BufferType.EDITABLE)
                    editPurchaseClientId.setText("${c.getString(2)}", TextView.BufferType.EDITABLE)
                    editPurchasePetName.setText("${c.getString(3)}", TextView.BufferType.EDITABLE)
                    editPurchaseClientFirstName.setText("${c.getString(4)}", TextView.BufferType.EDITABLE)
                    editPurchaseClientLastName.setText("${c.getString(5)}", TextView.BufferType.EDITABLE)
                }else{
                    Toast.makeText(activity, "Invalid Id Error, enter a different Id", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(activity, "Data Ready for Edit", Toast.LENGTH_LONG).show()
            }else{
                val a = dm.searchIdPurchase(editIdPurchase.text.toString().toInt())
                if(a.count > 0){
                    val b = dm.searchIdPet(editPurchasePetId.text.toString().toInt())
                    if(b.count > 0){
                        val c = dm.searchIdClient(editPurchaseClientId.text.toString().toInt())
                        if(c.count > 0){
                            val d = dm.searchNamePet(editPurchasePetName.text.toString())
                            if(d.count > 0){
                                val e = dm.searchNameClient(editPurchaseClientFirstName.text.toString(), editPurchaseClientLastName.text.toString())
                                if(e.count > 0){
                                    tid = editIdPurchase.text.toString().toInt()
                                    val tPetId = editPurchasePetId.text.toString().toInt()
                                    val tClientId = editPurchaseClientId.text.toString().toInt()
                                    val tPetName = editPurchasePetName.text.toString()
                                    val tClientFName = editPurchaseClientFirstName.text.toString()
                                    val tClientLName = editPurchaseClientLastName.text.toString()
                                    dm.updatePurchase(tid, tPetId, tClientId, tPetName, tClientFName, tClientLName)

                                    btnUpdatePurchase.text = "Edit Purchase"
                                    Toast.makeText(activity, "Data Edited to ${tid} ${tPetId} ${tClientId} ${tPetName} ${tClientFName} ${tClientLName}", Toast.LENGTH_LONG).show()
                                    editIdPurchase.text = null
                                    editPurchasePetId.text = null
                                    editPurchaseClientId.text = null
                                    editPurchasePetName.text = null
                                    editPurchaseClientFirstName.text = null
                                    editPurchaseClientLastName.text = null

                                    updateList(view)
                                }else{
                                    Toast.makeText(activity, "Invalid Client First or Last Name: ${editPurchaseClientFirstName.text} ${editPurchaseClientLastName.text}", Toast.LENGTH_LONG).show()
                                }
                            }else{
                                Toast.makeText(activity, "Invalid Pet Name: ${editPurchasePetName.text}", Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Toast.makeText(activity, "Invalid Client Id: ${editPurchaseClientId.text}", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(activity, "Invalid Pet Id: ${editPurchasePetId.text}", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(activity, "Invalid Id: ${editIdPurchase.text}", Toast.LENGTH_LONG).show()
                }
            }
        }

        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterUpdatePurchase = view.findViewById(R.id.txtDispUpdatePurchase) as TextView

        val c = dm.selectAllPurchases()
        var list = ""

        while(c.moveToNext()){
            list += "Id: " + c.getString(0) + "\n" + "Pet Id: " + c.getString(1) + "\n" + "Client Id: " + c.getString(2) + "\n" + "Pet Name: " +c.getString(3) + "\n" + "Client First Name: " + c.getString(4) + "\n" + "Client Last Name: " + c.getString(5) + "\n" + "\n------------------------------------------------------------\n \n"
        }

        textAfterUpdatePurchase.text = list

        val textResultsPets = view.findViewById(R.id.txtDispUpdatePurchasePet) as TextView

        val d = dm.selectAllPets()
        var list1 = ""

        while(d.moveToNext()){
            list1 += "Id: " + d.getString(0) + "\n" + "Species: " + d.getString(1) + "\n" + "Name: " + d.getString(2) + "\n" + "Age: " + d.getString(3) + "\n" + "Food: " + d.getString(4) + "\n"  + "\n------------------------------------------------------------\n \n"
        }

        textResultsPets.text = list1

        val textResultsClients = view.findViewById(R.id.txtDispUpdatePurchaseClient) as TextView

        val e = dm.selectAllClients()
        var list2 = ""

        while(e.moveToNext()){
            list2 += "Id: " + e.getString(0) + "\n" + "First Name: " + e.getString(1) + "\n" + "Last Name: " + e.getString(2) + "\n" + "Payment Method: " + e.getString(3) + "\n------------------------------------------------------------\n \n"
        }

        textResultsClients.text = list2
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragUpdatePurchase.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragUpdatePurchase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}