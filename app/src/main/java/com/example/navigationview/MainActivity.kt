package com.example.navigationview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
    fun enviarDatos(view: View){
        val intent= Intent(this,NavigationView::class.java)
        val usertxt=findViewById<EditText>(R.id.usertxt)
        val passtxt=findViewById<EditText>(R.id.passtxt)

        if(usertxt.text.toString()=="Jeandavid"&& passtxt.text.toString()=="admin")
        {
            intent.putExtra("usuario",usertxt.text.toString())
            intent.putExtra("password",passtxt.text.toString())
            intent.putExtra("rol","Administrador")
            startActivity(intent)
        }
        else if(usertxt.text.toString()=="JoseM"&& passtxt.text.toString()=="user")
        {
            intent.putExtra("usuario",usertxt.text.toString())
            intent.putExtra("password",passtxt.text.toString())
            intent.putExtra("rol","user")
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this,"Usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show()
        }

    }


}