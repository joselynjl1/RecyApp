package com.example.recyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import com.example.recyapp.UI.Activities.MainActivity
import com.example.recyapp.UI.Fragments.RegisterFragment

class LoginActivity : AppCompatActivity() {
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
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val botonRegistrase=findViewById<Button>(R.id.BtnRegistrar)
        botonRegistrase.setOnClickListener{
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