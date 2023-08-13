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
import androidx.lifecycle.ViewModelProvider
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.network.FirestoreServices
import com.example.recyapp.viewmodel.MaterialesViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_registrar_material.BtnCalcular
import kotlinx.android.synthetic.main.fragment_registrar_material.BtnEnviar

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_material, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrarMaterialFragment().apply {
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

/*
SuppressLint("StaticFieldLeak")
private val db = FirebaseFirestore.getInstance()
//private val spinnerData = mutableListOf<String>()

class RegistrarMaterialFragment : Fragment() {

    private lateinit var spinnerViewModel: MaterialesViewModel

    var firestoreServices = FirestoreServices()
    var materiales = MutableLiveData<MutableList<Materiales>>()
    var materialSelected: Materiales = Materiales()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar_material, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerViewModel = ViewModelProvider(requireActivity()).get(MaterialesViewModel::class.java)

        var btnEnviar = BtnEnviar.findViewById<Button>(R.id.BtnEnviar)
        var pesoIngresado = BtnEnviar.findViewById<EditText>(R.id.ETIngresarPeso)
        btnEnviar.isVisible = false
        val spinner = view.findViewById<Spinner>(R.id.SpinnerRegistrar)

        val cachedSpinnerData = spinnerViewModel.spinnerData

        if (cachedSpinnerData != null && spinnerViewModel.spinnerDataLoaded) {
            setupSpinner(spinner, cachedSpinnerData)
        } else {
            firestoreServices.getMaterialesFirestore().observeForever {
                materiales.value = it
            }

            val collectionRef = db.collection("Materiales")

            collectionRef.get().addOnSuccessListener { result ->
                val spinnerData = ArrayList<String>()
                for (document in result) {
                    val data = document.getString("nombreMaterial")
                    data?.let {
                        spinnerData.add(data)
                    }
                }

                spinnerViewModel.spinnerData = spinnerData
                spinnerViewModel.spinnerDataLoaded = true
                setupSpinner(spinner, spinnerData)
            }
        }

        val BotonCalc = BtnCalcular.findViewById<Button>(R.id.BtnCalcular)

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

    private fun setupSpinner(spinner: Spinner, data: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
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
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tu lógica cuando no se selecciona nada en el Spinner
            }
        }
        spinner.adapter = adapter
    }
}

 */
