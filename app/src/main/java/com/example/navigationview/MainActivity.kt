package com.example.navigationview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(){

    val url = "https://my-json-server.typicode.com/JeanThePunisher/ArchivosJson/usuarios"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)


    }
    fun enviarDatos(view: View){
        val queue = Volley.newRequestQueue(this)
        val intent= Intent(this,NavigationView::class.java)
        val data=findViewById<EditText>(R.id.usertxt)
        val pass=findViewById<EditText>(R.id.passtxt)

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                val users = JSONArray(response.toString());
                for(i in 0 until users.length())
                {
                    val user: JSONObject = users.getJSONObject(i);
                    if(data.text.toString() == user.getString("usuario"))
                    {
                        if (pass.text.toString() == user.getString("contraseña"))
                        {
                            intent.putExtra("id",user.getString("id"))
                            intent.putExtra("usuario",user.getString("usuario"))
                            intent.putExtra("contraseña",user.getString("contraseña"))
                            intent.putExtra("rol",user.getString("rol"))
                            startActivity(intent)
                        }

                    }
                }
            },
            {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            })
        queue.add(request)

    }

}

