package com.example.recyapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import com.example.recyapp.UI.Activities.MainActivity
import com.example.recyapp.UI.Fragments.RegisterFragment
import com.example.recyapp.network.FirestoreServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var clave: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*se debe comentar install splash screen cuando queremos creamos
        colecciones en la bd):
        y borrar (android:theme="@style/Theme.App.SplashTheme")
        en el AndroidManifest
         */
        installSplashScreen()
        setContentView(R.layout.activity_login)

        val botonIngresar: Button =findViewById(R.id.BtnIngresar)
        botonIngresar.setOnClickListener{
            loguearse()
        }

        email=findViewById(R.id.ETCorreoInicioSesion)
        clave=findViewById(R.id.ETPasswordInicioSesion)

        auth=FirebaseAuth.getInstance()

        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        Toast.makeText(this, "Iniciando Sesion", Toast.LENGTH_SHORT).show()
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser
        saveAuthToken(user!!.uid)

        val botonRegistrase=findViewById<Button>(R.id.BtnRegistrar)
        botonRegistrase.setOnClickListener{
            cambiarFragmentRegistrarse(fragment = RegisterFragment())
        }

    }

    private fun saveAuthToken(userId: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("user_id", userId)
        editor.apply()
    }

    private fun loguearse() {
        val userEmail:String=email.text.toString()
        val userClave:String=clave.text.toString()
        if (!TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(userClave)) {
            auth.signInWithEmailAndPassword(userEmail, userClave)
                .addOnCompleteListener(this) {
                        task ->
                    if (task.isSuccessful) {
                        action()
                    } else {
                        Toast.makeText(this,"Error al ingresar", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun action() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    private fun cambiarFragmentRegistrarse(fragment: RegisterFragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_Login, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    //MATERIALESSS

    /*val jsonMateriales= JSONArray(
       "    [\n" +
               "        {\n" +
               "            \"nombreMaterial\" : \"Plasticos\",\n" +
               "            \"valorMaterial\" : \"100\",\n" +
               "            \"imagenurlMaterial\" : \"https://www.ambienteplastico.com/wp-content/uploads/2022/05/foto_enzima_030522-780x470.jpg\"\n" +
               "        },\n" +
               "        {\n" +
               "             \"nombreMaterial\" : \"Carton\",\n" +
               "            \"valorMaterial\" : \"200\",\n" +
               "            \"imagenurlMaterial\" : \"https://aseca.com/wp-content/uploads/beige-g8da7504a1_640-1.jpg\"\n" +
               "        },\n" +
               "        {\n" +
               "            \"nombreMaterial\" : \"Envases Metalicos\",\n" +
               "            \"valorMaterial\" : \"150\",\n" +
               "            \"imagenurlMaterial\" : \"https://previews.123rf.com/images/ockra/ockra1502/ockra150200106/36093063-envases-met%C3%A1licos-varios-para-su-reciclaje.jpg\"\n" +
               "        },\n" +
               "        {\n" +
               "            \"nombreMaterial\" : \"Papel\",\n" +
               "            \"valorMaterial\" : \"50\",\n" +
               "            \"imagenurlMaterial\" : \"https://stakeholders.com.pe/wp-content/uploads/2019/01/reciclaje-de-papel-1-1024x655.jpg\"\n" +
               "        },\n" +
               "        {\n" +
               "            \"nombreMaterial\" : \"Cobre\",\n" +
               "            \"valorMaterial\" : \"400\",\n" +
               "            \"imagenurlMaterial\" : \"https://www.derichebourgespana.com/wp-content/uploads/2019/02/cobre.jpg\"\n" +
               "        }\n" +
               "    ]"
   )

   val firebaseFirestore= FirebaseFirestore.getInstance();
   for (i in 0 until jsonMateriales.length()){
       val aux=jsonMateriales.get(i) as JSONObject
       var materiales= Materiales()

       materiales.nombreMaterial=aux.getString("nombreMaterial")
       materiales.valorMaterial=aux.getInt("valorMaterial")
       materiales.imagenurlMaterial=aux.getString("imagenurlMaterial")

       firebaseFirestore.collection("Materiales").document().set(materiales)
   }*/





    //RECOMPENSASSS
    /*val jsonRecompensas=JSONArray(
            """[
                {
                    "nombreRecompensa" : "Licuadora",
                    "valorRecompensa" : "800",
                    "imagen" : "https://p1.pxfuel.com/preview/867/1009/226/blender-mixer-juicer-food-processor.jpg"
                },
                {
                    "nombreRecompensa" : "USB 32Gb",
                    "valorRecompensa" : "2000",
                    "imagen" : "https://st4.depositphotos.com/4073345/23982/i/450/depositphotos_239826182-stock-photo-black-background-with-single-black.jpg"
                },
                {
                    "nombreRecompensa" : "Canasta basica",
                    "valorRecompensa" : "5000",
                    "imagen" : "https://cloudfront-us-east-1.images.arcpublishing.com/elcomercio/Z35S5NKWDVHJLPBE43MEOSIYVU.jpg"
                },
                {
                    "nombreRecompensa" : "Planta decorativa",
                    "valorRecompensa" : "500",
                    "imagen" : "https://hips.hearstapps.com/hmg-prod/images/plantas-de-interior-resistentes-2-1543351859.jpg"
                },
                {
                    "nombreRecompensa" : "Sixpack cerveza",
                    "valorRecompensa" : "3000",
                    "imagen" : "https://miamarket.pe/assets/uploads/1fa8d7178495be3f4654ee135e4aeb39.jpg"
                }
            ]"""
        )

        val firebaseFirestore= FirebaseFirestore.getInstance();
        for (i in 0 until jsonRecompensas.length()){
            val aux=jsonRecompensas.get(i) as JSONObject
            var recompensas= Recompensas()

            recompensas.nombreRecompensa=aux.getString("nombreRecompensa")
            recompensas.valorRecompensa=aux.getInt("valorRecompensa")
            recompensas.imagenurlRecompensa=aux.getString("imagen")

            firebaseFirestore.collection("Recompensas").document().set(recompensas)
        }*/


}