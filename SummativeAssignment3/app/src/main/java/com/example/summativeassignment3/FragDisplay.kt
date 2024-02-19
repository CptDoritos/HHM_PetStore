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
 * Use the [FragDisplay.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDisplay : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_display, container, false)

        val btnSearchPets = view.findViewById(R.id.btnSearchPets) as Button
        val editSearchPets = view.findViewById(R.id.editTextSearchPets) as EditText
        val textResultsPets = view.findViewById(R.id.textResultsPets) as TextView
        val radioAllPets = view.findViewById(R.id.rdioAllPets) as RadioButton
        val radioSearchPets = view.findViewById(R.id.rdioSearchPets) as RadioButton
        val laySearchPets = view.findViewById(R.id.searchHolderPets) as LinearLayout

        val dm = DataManager(requireActivity())
        updateList(view)

        radioAllPets.setOnClickListener {
            if(radioAllPets.isChecked){
                laySearchPets.visibility = View.GONE
            }
            updateList(view)
        }
        radioSearchPets.setOnClickListener {
            if(radioSearchPets.isChecked){
                laySearchPets.visibility = View.VISIBLE
                textResultsPets.text = "Your Search Results for Pets Will be Displayed Here"
            }
        }
        btnSearchPets.setOnClickListener {
            val c = dm.searchNamePet(editSearchPets.text.toString())
            if(c.count > 0){
                c.moveToNext()
                textResultsPets.text =
                    "Result = Id: ${c.getString(0)}\nSpecies: ${c.getString(1)}\nName: ${c.getString(2)}\nAge: ${c.getString(3)}\nFood: ${c.getString(4)}"
            }else{
                Toast.makeText(activity, "Unknown Animal Searched", Toast.LENGTH_LONG).show()
            }
        }

        //updateList(view)
        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textResultsPets = view.findViewById(R.id.textResultsPets) as TextView

        val c = dm.selectAllPets()
        var list = ""

        while(c.moveToNext()){
            list += "Id: " + c.getString(0) + "\n" + "Species: " + c.getString(1) + "\n" + "Name: " + c.getString(2) + "\n" + "Age: " +c.getString(3) + "\n" + "Food: " + c.getString(4) + "\n"  + "\n------------------------------------------------------------\n \n"
        }

        textResultsPets.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDisplay.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDisplay().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}