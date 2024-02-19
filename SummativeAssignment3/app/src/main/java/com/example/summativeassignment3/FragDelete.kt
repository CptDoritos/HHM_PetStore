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
 * Use the [FragDelete.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDelete : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_delete, container, false)

        val dm = DataManager(requireActivity())

        val editDeletePet = view.findViewById(R.id.editDeletePet) as EditText
        val btnDeletePet = view.findViewById(R.id.btnDeletePet) as Button

        updateList(view)

        btnDeletePet.setOnClickListener {
            dm.deletePet(editDeletePet.text.toString())
            Toast.makeText(activity, "Data from ${editDeletePet.text} deleted", Toast.LENGTH_LONG).show()
            editDeletePet.text = null
            updateList(view)
        }

        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterDeletePet = view.findViewById(R.id.txtDispDeletePet) as TextView

        val c = dm.selectAllPets()
        var list1 = ""

        while(c.moveToNext()){
            list1 += "Id: " + c.getString(0) + "\n" + "Species: " + c.getString(1) + "\n" + "Name: " + c.getString(2) + "\n" + "Age: " +c.getString(3) + "\n" + "Food: " + c.getString(4)  + "\n------------------------------------------------------------\n \n"
        }

        textAfterDeletePet.text = list1
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDelete.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDelete().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}