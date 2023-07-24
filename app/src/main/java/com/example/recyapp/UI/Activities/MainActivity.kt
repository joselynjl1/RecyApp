package com.example.recyapp.UI.Activities
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.recyapp.R
import com.example.recyapp.UI.Fragments.EditUserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //MUESTRA LOS TRES PUNTITOS DE (EDITAR Y CERRAR SESION)
        val toolbar = findViewById<Toolbar>(R.id.TBMain)
        setSupportActionBar(toolbar)

        /*
        bottomNavigation=findViewById(R.id.BTMNavigation)
        navController=findNavController(R.id.fragmentInicio)
        bottomNavigation.setupWithNavController(navController)*/

        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_frag) as NavHostFragment

        navController = navHostFragment.navController

        // Configurar la ActionBar con el NavController
        NavigationUI.setupActionBarWithNavController(this, navController)*/


        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(Home())
        binding.BTMNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.NAVHomeFragment->replaceFragment(Home())
                R.id.NavInformacion->replaceFragment(Informacion())
                R.id.NAVMapFragment->replaceFragment(Map())
                R.id.NAVRegistroMaterial->replaceFragment(Registro())
                R.id.NavRecompensas->replaceFragment(Recompensas())
                else->{
                }
            }
            true
         */
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
/*
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/

}
