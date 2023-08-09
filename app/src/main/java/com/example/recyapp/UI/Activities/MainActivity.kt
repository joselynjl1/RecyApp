package com.example.recyapp.UI.Activities
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recyapp.LoginActivity
import com.example.recyapp.R
import com.example.recyapp.UI.Fragments.EditUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarNavegacion()

    }

    fun configurarNavegacion(){
        //NAVEGACION DE LA APP
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentInicio) as NavHostFragment
        val navController = navHostFragment.navController
        // Configurar el BottomNavigationView para que refleje la navegación
        val bottomNavView = findViewById<BottomNavigationView>(R.id.BTMNavigation)
        bottomNavView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.bnav_menuopciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.editar_perfil -> {
                cambiarFragmentEditar(fragment = EditUserFragment())
                true
            }
            R.id.cerrar_sesion -> {
                val intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cambiarFragmentEditar(fragment: EditUserFragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_main, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
