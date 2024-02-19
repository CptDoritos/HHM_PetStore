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
 * Use the [FragInsert.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragInsert : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_frag_insert, container, false)

        val dm = DataManager(requireActivity())
        val btnInsert = view.findViewById(R.id.btnInsert) as Button
        val editSpecies = view.findViewById(R.id.editSpecies) as EditText
        val editName = view.findViewById(R.id.editName) as EditText
        val editAge = view.findViewById(R.id.editAge) as EditText
        val editFood = view.findViewById(R.id.editFood) as EditText

        btnInsert.setOnClickListener {

            var pet1 = Pet(editSpecies.text.toString(), editName.text.toString(), editAge.text.toString().toInt(), editFood.text.toString())
            dm.insertPet(pet1.Species, pet1.Name, pet1.Age, pet1.Food)
            Toast.makeText(activity, "${pet1.Species} ${pet1.Name} ${pet1.Age} ${pet1.Food} Inserted", Toast.LENGTH_LONG).show()
            editSpecies.text = null
            editName.text = null
            editAge.text = null
            editFood.text = null

            //dm.resetTablePets()
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
         * @return A new instance of fragment FragInsert.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragInsert().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}