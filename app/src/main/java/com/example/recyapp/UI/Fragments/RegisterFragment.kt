package com.example.recyapp.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recyapp.R
import com.example.recyapp.LoginActivity
import com.example.recyapp.model.Usuario
import com.example.recyapp.network.FirestoreServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {

    private lateinit var txtnombres: EditText
    private lateinit var txtusuario: EditText
    private lateinit var txtcorreo: EditText
    private lateinit var txtcontraenia: EditText
    //var firestoreServices = FirestoreServices()

    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var boton_registro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        edit_email = findViewById(R.id.email)
        edit_clave = findViewById(R.id.clave)
        edit_nombres = findViewById(R.id.nombresET)
        edit_apellidos = findViewById(R.id.apellidosET)
        edit_dni = findViewById(R.id.dniET)
        auth = FirebaseAuth.getInstance()
        boton_registro = findViewById(R.id.btn_registro)
        boton_registro.setOnClickListener {
            crearCuenta()
        }
         */

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_register, container, false)

        txtnombres=view.findViewById(R.id.ETFullName)
        txtcorreo=view.findViewById(R.id.ETemail)
        txtusuario=view.findViewById(R.id.ETUsuario)
        txtcontraenia=view.findViewById(R.id.ETPassword)
        auth = FirebaseAuth.getInstance()
        boton_registro = view.findViewById(R.id.BtnRegistrarse)
        boton_registro.setOnClickListener {
            registrarCuenta()
        }

        return view
    }

    private fun registrarCuenta() {

        val usuario=txtusuario.text.toString()
        val nombrecompleto=txtnombres.text.toString()
        val contrasenia=txtcontraenia.text.toString()
        val correo=txtcorreo.text.toString()

        if (!TextUtils.isEmpty(correo)
            && !TextUtils.isEmpty(contrasenia)) {

            auth.createUserWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user!!.uid
                        verifyEmail(user)

                        val database = FirebaseFirestore.getInstance()
                        val cuenta = Usuario()
                        cuenta.usuario = usuario
                        cuenta.correo = correo
                        cuenta.nombrecompleto = nombrecompleto
                        cuenta.contrasenia = contrasenia
                        cuenta.puntos = 0;
                        database.collection("Usuario").document(uid).set(cuenta)
                        action()
                    }
                }
        }
    }

    private fun action() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
    }

    private fun verifyEmail(user: FirebaseUser?) {
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Se envió el correo electrónico", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Error al enviar el correo electrónico", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Boton que se puede usar cuando ya se registro y quiere ingresar a su sesion
        val botonDirigirInicioSesion:Button=view.findViewById(R.id.BtnIniciar)
        botonDirigirInicioSesion.setOnClickListener{
            //redirige al login
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}