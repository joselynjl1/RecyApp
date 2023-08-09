package com.example.recyapp.UI.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.recyapp.R
import com.example.recyapp.LoginActivity

class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //boton REGISTRARSE cuando un usuario se registra correctamente
        val botonRegistroUsuario:Button=view.findViewById(R.id.BtnRegistrarse)
        botonRegistroUsuario.setOnClickListener{
            Toast.makeText(context, "Usuario Registrado", Toast.LENGTH_SHORT).show()
            //Toast
        }

        //Boton que se puede usar cuando ya se registro y quiere ingresar a su sesion
        val botonDirigirInicioSesion:Button=view.findViewById(R.id.BtnIniciar)
        botonDirigirInicioSesion.setOnClickListener{
            //redirige al login
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {

            }
    }
}