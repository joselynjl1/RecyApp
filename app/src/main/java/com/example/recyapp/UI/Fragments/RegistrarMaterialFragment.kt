package com.example.recyapp.UI.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Usuario
import com.example.recyapp.network.FirestoreServices
import com.example.recyapp.viewmodel.UserViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_registrar_material.ETPuntos

//@SuppressLint("StaticFieldLeak")

class RegistrarMaterialFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private val spinnerData = mutableListOf<String>()
    var firestoreServices = FirestoreServices()
    var materiales = MutableLiveData<MutableList<Materiales>>()
    var materialSelected: Materiales = Materiales()
    var Resultado:Double=0.0

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )
            .get(UserViewModel::class.java)
    }


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

        val btnEnviar = view.findViewById<Button>(R.id.BtnEnviar)
        val pesoIngresado = view.findViewById<EditText>(R.id.ETIngresarPeso)
        btnEnviar.isVisible = false
        val spinner = view.findViewById<Spinner>(R.id.SpinnerRegistrar)

        firestoreServices.getMaterialesFirestore().observeForever {
            materiales.value = it
        }
        val collectionRef = db.collection("Materiales")

        collectionRef.get().addOnSuccessListener { result ->
            for (document in result) {

                val data = document.getString("nombreMaterial")
                data?.let {
                    spinnerData.add(data)
                }
            }
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerData)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    materialSelected = materiales.value!!.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            spinner.adapter = adapter

            val BotonCalc = view.findViewById<Button>(R.id.BtnCalcular)

            BotonCalc.setOnClickListener {

                Resultado = pesoIngresado.text.toString()
                    .toDouble() * materialSelected.valorMaterial.toDouble()
                val tvpuntos = view.findViewById<EditText>(R.id.ETPuntos)
                Toast.makeText(requireContext(), Resultado.toString(), Toast.LENGTH_SHORT).show()

                (tvpuntos as TextView).text = Resultado.toString()
                tvpuntos.isEnabled = false
                BotonCalc.isVisible = false
                btnEnviar.isVisible = true
            }
            btnEnviar.setOnClickListener{
                showConfirmDialog()
            }
        }

    }
    fun showConfirmDialog(){
        val confirmDialog = Dialog(ETPuntos.context)
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        confirmDialog.setCancelable(false)
        confirmDialog.setContentView(R.layout.custom_message_dialog)
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnAceptar = confirmDialog.findViewById<Button>(R.id.btnAceptarMensaje)
        confirmDialog.show()

        btnAceptar.setOnClickListener {
            var currentUser: Usuario
            viewModel.getCurrentUser().observe(viewLifecycleOwner) {
                currentUser = it
                Log.d("PUNTAJE",currentUser.puntos.toString())
                viewModel.updateUserPoints(currentUser.puntos + Resultado.toInt()).observe(viewLifecycleOwner){
                    Log.d("Puntos actualizados","puntaje")
                    confirmDialog.dismiss()
                    Toast.makeText(ETPuntos.context, "Puntaje actualizado: ${it.puntos}", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }


}
