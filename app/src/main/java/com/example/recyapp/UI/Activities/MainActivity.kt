package com.example.recyapp.UI.Activities
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.recyapp.R
import com.example.recyapp.UI.Fragments.EditUserFragment

class MainActivity : AppCompatActivity() {

    /*private lateinit var binding: ActivityMainBinding*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.TBMain)
        setSupportActionBar(toolbar)

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
}
