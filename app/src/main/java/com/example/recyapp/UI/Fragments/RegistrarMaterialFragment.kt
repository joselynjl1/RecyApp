package com.example.recyapp.UI.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.network.FirestoreServices
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_registrar_material.BtnCalcular
import kotlinx.android.synthetic.main.fragment_registrar_material.BtnEnviar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@SuppressLint("StaticFieldLeak")
private val db = FirebaseFirestore.getInstance()
private val spinnerData = mutableListOf<String>()

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrarMaterialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrarMaterialFragment : Fragment() {

    var firestoreServices = FirestoreServices()
    var materiales = MutableLiveData<MutableList<Materiales>>()
    var materialSelected: Materiales = Materiales()


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
        return inflater.inflate(R.layout.fragment_registrar_material, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrarMaterialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrarMaterialFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnEnviar = BtnEnviar.findViewById<Button>(R.id.BtnEnviar)
        var pesoIngresado= BtnEnviar.findViewById<EditText>(R.id.ETIngresarPeso)
        btnEnviar.isVisible = false
        val spinner = view.findViewById<Spinner>(R.id.SpinnerRegistrar)
        firestoreServices.getMaterialesFirestore().observeForever {
            materiales.value = it
        }
        val collectionRef= db.collection("Materiales")

        collectionRef.get().addOnSuccessListener { result ->
            for (document in result) {

                val data = document.getString("nombreMaterial")
                data?.let {
                    spinnerData.add(data)
                }
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //val selectedItem = parent?.getItemAtPosition(position).toString()
                    //Toast.makeText(requireContext(), .valorMaterial.toString(), Toast.LENGTH_SHORT).show()
                    materialSelected = materiales.value!!.get(position)

                    // do something with selected key
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // do something when unselected
                }
            }

            spinner.adapter = adapter

            val BotonCalc=BtnCalcular.findViewById<Button>(R.id.BtnCalcular)

            BotonCalc.setOnClickListener {

                val Resultado= pesoIngresado.text
                //val tvpuntos = BtnCalcular.findViewById<EditText>(R.id.ETPuntos)
                Toast.makeText(requireContext(), Resultado, Toast.LENGTH_SHORT).show()
                //tvpuntos.text = Resultado.toString() as Editable
                //tvpuntos.isEnabled = false

                BotonCalc.isVisible = false
                //btnEnviar.isVisible = true

            }
        }

    }
}