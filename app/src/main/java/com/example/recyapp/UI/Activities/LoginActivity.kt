package com.example.recyapp.UI.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.recyapp.R
import com.example.recyapp.UI.Fragments.RegisterFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_login)

        val botonIngresar:Button=findViewById(R.id.BtnIngresar)
        botonIngresar.setOnClickListener{
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val botonRegistrase=findViewById<Button>(R.id.BtnRegistrar)
        botonRegistrase.setOnClickListener{
            //val fragmentRegistrarse=RegisterFragment()
            //cambiarFragmentRegistrase(fragmentRegistrarse)
            cambiarFragmentRegistrarse(fragment = RegisterFragment())
        }
    }

    private fun cambiarFragmentRegistrarse(fragment: RegisterFragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_Login, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}