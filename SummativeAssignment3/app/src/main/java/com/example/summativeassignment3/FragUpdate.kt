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
 * Use the [FragUpdate.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragUpdate : Fragment() {
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
        val dm = DataManager(requireActivity())
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_update, container, false)

        val editIdPet = view.findViewById(R.id.editUpdateIDPet) as EditText
        val btnUpdatePet = view.findViewById(R.id.btnUpdatePet) as Button
        val editSpecies = view.findViewById(R.id.editUpdateSpecies) as EditText
        val editNamePet = view.findViewById(R.id.editUpdatePetName) as EditText
        val editAge = view.findViewById(R.id.editUpdateAge) as EditText
        val editFood = view.findViewById(R.id.editUpdateFood) as EditText

        updateList(view)

        btnUpdatePet.setOnClickListener {
            var tid = 0
            if(btnUpdatePet.text == "Edit Pet"){
                tid = (editIdPet.text.toString().toInt())
                val c = dm.searchIdPet(editIdPet.text.toString().toInt())

                if(c.count > 0){
                    c.moveToNext()
                    btnUpdatePet.text = "Update Pet"

                    editSpecies.setText("${c.getString(1)}", TextView.BufferType.EDITABLE)
                    editNamePet.setText("${c.getString(2)}", TextView.BufferType.EDITABLE)
                    editAge.setText("${c.getString(3)}", TextView.BufferType.EDITABLE)
                    editFood.setText("${c.getString(4)}", TextView.BufferType.EDITABLE)
                }else{
                    Toast.makeText(activity, "Invalid Id Error, enter a different Id", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(activity, "Data Ready for Edit", Toast.LENGTH_LONG).show()
            }else{
                tid = editIdPet.text.toString().toInt()
                val tSpecies = editSpecies.text.toString()
                val tName = editNamePet.text.toString()
                val tAge = editAge.text.toString()
                val tFood = editFood.text.toString()
                dm.updatePet(tid, tSpecies, tName, tAge, tFood)

                btnUpdatePet.text = "Edit Pet"
                Toast.makeText(activity, "Data edited to ${editSpecies.text} ${editNamePet.text} ${editAge.text} ${editFood.text}", Toast.LENGTH_LONG).show()
                editSpecies.text = null
                editNamePet.text = null
                editAge.text = null
                editFood.text = null

                updateList(view)
            }
        }
        return view
    }

    fun updateList(view: View){
        val dm = DataManager(requireActivity())
        val textAfterUpdatePet = view.findViewById(R.id.txtDispUpdatePet) as TextView

        val c = dm.selectAllPets()
        var list = ""

        while(c.moveToNext()){
            list += "Id: " + c.getString(0) + "\n" + "Species: " + c.getString(1) + "\n" + "Name: " + c.getString(2) + "\n" + "Age: " +c.getString(3) + "\n" + "Food: " + c.getString(4) + "\n" + "\n------------------------------------------------------------\n \n"
        }

        textAfterUpdatePet.text = list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragUpdate.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragUpdate().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}