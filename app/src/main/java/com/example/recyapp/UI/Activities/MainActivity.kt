package com.example.recyapp.UI.Activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.recyapp.R
import com.example.recyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    /*private lateinit var binding: ActivityMainBinding*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }
/*private fun replaceFragment(fragment: Fragment){

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.linearLayout_main,fragment)
    fragmentTransaction.commit()

    }
}*/