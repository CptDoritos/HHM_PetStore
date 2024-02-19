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
 * Use the [FragInsertPurchase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragInsertPurchase : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_insert_purchase, container, false)

        val dm = DataManager(requireActivity())
        val btnInsertPurchase = view.findViewById(R.id.btnInsertPurchase) as Button
        val editPurchasePetId = view.findViewById(R.id.editPurchasePetId) as EditText
        val editPurchaseClientId = view.findViewById(R.id.editPurchaseClientId) as EditText
        val editPurchasePetName = view.findViewById(R.id.editPurchasePetName) as EditText
        val editPurchaseClientFirstName = view.findViewById(R.id.editPurchaseClientFirstName) as EditText
        val editPurchaseClientLastName = view.findViewById(R.id.editPurchaseClientLastName) as EditText

        btnInsertPurchase.setOnClickListener {

            val a = dm.searchIdPet(editPurchasePetId.text.toString().toInt())
            if(a.count > 0){
                val b = dm.searchIdClient(editPurchaseClientId.text.toString().toInt())
                if(b.count > 0){
                    val c = dm.searchNamePet(editPurchasePetName.text.toString())
                    if(c.count > 0){
                        val d = dm.searchNameClient(editPurchaseClientFirstName.text.toString(), editPurchaseClientLastName.text.toString())
                        if(d.count > 0){
                            var purchase1 = Purchases(editPurchasePetId.text.toString().toInt(), editPurchaseClientId.text.toString().toInt(), editPurchasePetName.text.toString(), editPurchaseClientFirstName.text.toString(), editPurchaseClientLastName.text.toString())
                            dm.insertPurchase(purchase1.PetId, purchase1.clientId, purchase1.petName, purchase1.clientFirstName, purchase1.clientLastName)
                            Toast.makeText(activity, "${purchase1.PetId} ${purchase1.clientId} ${purchase1.petName} ${purchase1.clientFirstName} ${purchase1.clientLastName} Success", Toast.LENGTH_LONG).show()
                            editPurchasePetId.text = null
                            editPurchaseClientId.text = null
                            editPurchasePetName.text = null
                            editPurchaseClientFirstName.text = null
                            editPurchaseClientLastName.text = null
                        }else{
                            Toast.makeText(activity, "Unknown Client First or Last Name: ${editPurchaseClientFirstName.text} ${editPurchaseClientLastName.text}", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(activity, "Unknown Pet Name: ${editPurchasePetName.text}", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(activity, "Unknown Client Id: ${editPurchaseClientId.text}", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity, "Unknown Pet Id: ${editPurchasePetId.text}", Toast.LENGTH_LONG).show()
            }



            //dm.resetTablePurchases()
            //Toast.makeText(context, "Table Reset!", Toast.LENGTH_LONG).show()
        }

        updateList(view)
        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textResultsPets = view.findViewById(R.id.InsertPurchasePetList) as TextView

        val c = dm.selectAllPets()
        var list1 = ""

        while(c.moveToNext()){
            list1 += "Id: " + c.getString(0) + "\n" + "Species: " + c.getString(1) + "\n" + "Name: " + c.getString(2) + "\n" + "Age: " +c.getString(3) + "\n" + "Food: " + c.getString(4) + "\n"  + "\n------------------------------------------------------------\n \n"
        }

        textResultsPets.text = list1

        val textResultsClients = view.findViewById(R.id.InsertPurchaseClientList) as TextView

        val d = dm.selectAllClients()
        var list2 = ""

        while(d.moveToNext()){
            list2 += "Id: " + d.getString(0) + "\n" + "First Name: " + d.getString(1) + "\n" + "Last Name: " + d.getString(2) + "\n" + "Payment Method: " + d.getString(3) + "\n------------------------------------------------------------\n \n"
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
         * @return A new instance of fragment FragInsertPurchase.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragInsertPurchase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}