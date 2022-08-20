package com.example.navigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray
import org.json.JSONObject


class NavigationView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerlayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacionview)
        val url = "https://my-json-server.typicode.com/JeanThePunisher/archivo-json-2/items"
        val queue = Volley.newRequestQueue(this)
        val bundle = intent.extras
        var toolbar: Toolbar = findViewById(R.id.toolbar_main)

        setSupportActionBar(toolbar)
        drawerlayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerlayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        var headerView: View = navigationView.getHeaderView(0)
        var userName: TextView = headerView.findViewById(R.id.user_name)
        userName.setText(bundle?.getString("usuario"))
        var imageView: ImageView = navigationView.getHeaderView(0).findViewById(R.id.image_view)
        var menu: Menu = navigationView.menu


        if (bundle?.getString("id") != null) {
            Toast.makeText(
                this,
                "Bienvenido " + bundle?.getString("rol") +" "+ bundle?.getString("usuario"),
                Toast.LENGTH_SHORT
            ).show()
            imageView.setImageResource(R.drawable.admin)
            val request = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val ItemsMenu = JSONArray(response.toString());
                    for (i in 0 until ItemsMenu.length()) {
                        val items: JSONObject = ItemsMenu.getJSONObject(i)
                        if (bundle?.getString("id") == "1" && items.getString("id") == "1") {
                            menu.add(items.getString("item1"))
                            menu.add(items.getString("item2"))
                            menu.add(items.getString("item3"))
                            menu.add(items.getString("item4"))
                            menu.add(items.getString("item5"))
                            menu.add(items.getString("item6"))
                        } else if (bundle?.getString("id") == "2" && items.getString("id") == "2") {
                            menu.add(items.getString("item1"))
                            menu.add(items.getString("item2"))
                            menu.add(items.getString("item3"))
                            menu.add(items.getString("item4"))
                            imageView.setImageResource((R.drawable.user))
                        } else if (bundle?.getString("id") == "3" && items.getString("id") == "3") {
                            menu.add(items.getString("item1"))
                            menu.add(items.getString("item2"))
                            menu.add(items.getString("item3"))
                            imageView.setImageResource((R.drawable.maes))
                        } else if (bundle?.getString("id") == "4" && items.getString("id") == "4") {
                            menu.add(items.getString("item1"))
                            menu.add(items.getString("item2"))
                            menu.add(items.getString("item3"))
                            menu.add(items.getString("item4"))
                            menu.add(items.getString("item5"))
                            imageView.setImageResource((R.drawable.emple))
                        }
                    }
                },
                {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                })
            queue.add(request)


        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}
