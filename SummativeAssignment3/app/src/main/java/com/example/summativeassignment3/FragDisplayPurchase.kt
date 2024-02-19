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
 * Use the [FragDisplayPurchase.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDisplayPurchase : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_display_purchase, container, false)

        val btnSearchPets = view.findViewById(R.id.btnSearchPurchases) as Button
        val editSearchPurchases1 = view.findViewById(R.id.editTextSearchPurchases1) as EditText
        val editSearchPurchases2 = view.findViewById(R.id.editTextSearchPurchases2) as EditText
        val textResultsPurchases = view.findViewById(R.id.textResultsPurchases) as TextView
        val radioAllPurchases = view.findViewById(R.id.rdioAllPurchases) as RadioButton
        val radioSearchPurchaseId = view.findViewById(R.id.rdioSearchPurchaseByPurchaseId) as RadioButton
        val radioSearchByPetIdPurchase = view.findViewById(R.id.rdioSearchPurchaseByPetId) as RadioButton
        val radioSearchByClientIdPurchase = view.findViewById(R.id.rdioSearchPurchaseByClientId) as RadioButton
        val radioSearchByPetNamePurchase = view.findViewById(R.id.rdioSearchPurchaseByPetName) as RadioButton
        val radioSeearchByClientFirstAndLastNamePurchase = view.findViewById(R.id.rdioSearchPurchaseByClientFirstAndLastName) as RadioButton
        val laySearchPurchases = view.findViewById(R.id.searchHolderPurchases) as LinearLayout

        val dm = DataManager(requireActivity())

        var searchInput: Int = 0

        updateList(view)

        radioAllPurchases.setOnClickListener {
            if(radioAllPurchases.isChecked){
                laySearchPurchases.visibility = View.GONE
                searchInput = 0
            }
            updateList(view)
        }

        radioSearchPurchaseId.setOnClickListener {
            if(radioSearchPurchaseId.isChecked){
                laySearchPurchases.visibility = View.VISIBLE
                textResultsPurchases.text = "Your Search Results for Purchases Will be Displayed Here"
                searchInput = 1
            }
        }

        radioSearchByPetIdPurchase.setOnClickListener {
            if(radioSearchByPetIdPurchase.isChecked){
                laySearchPurchases.visibility = View.VISIBLE
                textResultsPurchases.text = "Your Search Results for Purchases Will be Displayed Here"
                searchInput = 2
            }
        }

        radioSearchByClientIdPurchase.setOnClickListener {
            if(radioSearchByClientIdPurchase.isChecked){
                laySearchPurchases.visibility = View.VISIBLE
                textResultsPurchases.text = "Your Search Results for Purchases Will be Displayed Here"
                searchInput = 3
            }
        }

        radioSearchByPetNamePurchase.setOnClickListener {
            if(radioSearchByPetNamePurchase.isChecked){
                laySearchPurchases.visibility = View.VISIBLE
                textResultsPurchases.text = "Your Search Results for Purchases Will be Displayed Here"
                searchInput = 4
            }
        }

        radioSeearchByClientFirstAndLastNamePurchase.setOnClickListener {
            if(radioSeearchByClientFirstAndLastNamePurchase.isChecked){
                laySearchPurchases.visibility = View.VISIBLE
                textResultsPurchases.text = "Your Search Results for Purchases Will be Displayed Here"
                searchInput = 5
            }
        }

        btnSearchPets.setOnClickListener {
            if(searchInput == 1){
                Toast.makeText(activity, "Searching by Purchase Id", Toast.LENGTH_LONG).show()
                val a = dm.searchIdPurchase(editSearchPurchases1.text.toString().toInt())
                if(a.count > 0){
                    var list = ""
                    while(a.moveToNext()){
                        list += "Id: " + a.getString(0) + "\n" + "Pet Id: " + a.getString(1) + "\n" + "Client Id: " + a.getString(2) + "\n" + "Pet Name: " + a.getString(3) + "\n" + "Client First Name: " + a.getString(4) + "\n"  + "Client Last Name: " + a.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
                    }
                    textResultsPurchases.text = list
                }else{
                    Toast.makeText(activity, "Unknown Purchase Searched by Purchase Id: ${editSearchPurchases1.text}", Toast.LENGTH_LONG).show()
                }
            }else if(searchInput == 2){
                Toast.makeText(activity, "Searching by Pet Id", Toast.LENGTH_LONG).show()
                val b = dm.searchPurchasePetId(editSearchPurchases1.text.toString().toInt())
                if(b.count > 0){
                    var list = ""
                    while(b.moveToNext()){
                        list += "Id: " + b.getString(0) + "\n" + "Pet Id: " + b.getString(1) + "\n" + "Client Id: " + b.getString(2) + "\n" + "Pet Name: " + b.getString(3) + "\n" + "Client First Name: " + b.getString(4) + "\n"  + "Client Last Name: " + b.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
                    }
                    textResultsPurchases.text = list
                }else{
                    Toast.makeText(activity, "Unknown Purchase Searched by Pet Id: ${editSearchPurchases1.text}", Toast.LENGTH_LONG).show()
                }
            }else if(searchInput == 3){
                Toast.makeText(activity, "Searching by Client Id", Toast.LENGTH_LONG).show()
                val c = dm.searchPurchaseClientId(editSearchPurchases1.text.toString().toInt())
                if(c.count > 0){
                    var list = ""
                    while(c.moveToNext()){
                        list += "Id: " + c.getString(0) + "\n" + "Pet Id: " + c.getString(1) + "\n" + "Client Id: " + c.getString(2) + "\n" + "Pet Name: " + c.getString(3) + "\n" + "Client First Name: " + c.getString(4) + "\n"  + "Client Last Name: " + c.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
                    }
                    textResultsPurchases.text = list
                }else{
                    Toast.makeText(activity, "Unknown Purchase Searched by Client Id: ${editSearchPurchases1.text}", Toast.LENGTH_LONG).show()
                }
            }else if(searchInput == 4){
                Toast.makeText(activity, "Searching by Pet Name", Toast.LENGTH_LONG).show()
                val d = dm.searchPurchaseNamePet(editSearchPurchases1.text.toString())
                if(d.count > 0){
                    var list = ""
                    while(d.moveToNext()){
                        list += "Id: " + d.getString(0) + "\n" + "Pet Id: " + d.getString(1) + "\n" + "Client Id: " + d.getString(2) + "\n" + "Pet Name: " + d.getString(3) + "\n" + "Client First Name: " + d.getString(4) + "\n"  + "Client Last Name: " + d.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
                    }
                    textResultsPurchases.text = list
                }else{
                    Toast.makeText(activity, "Unknown Purchase Searched by Pet Name: ${editSearchPurchases1.text}", Toast.LENGTH_LONG).show()
                }
            }else if(searchInput == 5){
                Toast.makeText(activity, "Searching by Client First and Last Name", Toast.LENGTH_LONG).show()
                val e = dm.searchPurchaseFirstAndLastNameClient(editSearchPurchases1.text.toString(), editSearchPurchases2.text.toString())
                if(e.count > 0){
                    var list = ""
                    while(e.moveToNext()){
                        list += "Id: " + e.getString(0) + "\n" + "Pet Id: " + e.getString(1) + "\n" + "Client Id: " + e.getString(2) + "\n" + "Pet Name: " + e.getString(3) + "\n" + "Client First Name: " + e.getString(4) + "\n"  + "Client Last Name: " + e.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
                    }
                    textResultsPurchases.text = list
                }else{
                    Toast.makeText(activity, "Unknown Purchase Searched Client First or Last Name: ${editSearchPurchases1.text}", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(activity, "Unknown Error Occurred: Please Try Again", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textResultsPurchases = view.findViewById(R.id.textResultsPurchases) as TextView

        val c = dm.selectAllPurchases()
        var list = ""

        while(c.moveToNext()){
            list += "Id: " + c.getString(0) + "\n" + "Pet Id: " + c.getString(1) + "\n" + "Client Id: " + c.getString(2) + "\n" + "Pet Name: " +c.getString(3) + "\n" + "Client First Name: " + c.getString(4) + "\n"  + "Client Last Name: " + c.getString(5)  + "\n" +"\n------------------------------------------------------------\n \n"
        }

        textResultsPurchases.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDisplayPurchase.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDisplayPurchase().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}