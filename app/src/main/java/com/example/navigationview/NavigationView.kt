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
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class NavigationView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerlayout: DrawerLayout
    lateinit var toggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacionview)

        val bundle=intent.extras
        var toolbar: Toolbar =findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        drawerlayout=findViewById(R.id.drawer_layout)
        toggle= ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerlayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        var navigationView: NavigationView =findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        var headerView: View=navigationView.getHeaderView(0)
        var userName:TextView=headerView.findViewById(R.id.user_name)
        userName.setText(bundle?.getString("usuario"))
        var imageView:ImageView=headerView.findViewById(R.id.image_view)
        var menu:Menu=navigationView.menu

        if(bundle?.getString("rol")=="Administrador"){
            imageView.setImageResource(R.drawable.admin)
            menu.add("Red")
            menu.add("Estadisticas")
            menu.add("Contrato")
            menu.add("Configuración")
            menu.add("Salir")
        }else if (bundle?.getString("rol")=="user"){
            imageView.setImageResource(R.drawable.user)
            menu.add("Perfil")
            menu.add("Calificaciones")
            menu.add("Libros")
            menu.add("Configuración")
            menu.add("Salir")
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.title){

            "Estadisticas"-> {
                fragment= DatosUser()
                Toast.makeText(this,"Estadísticas", Toast.LENGTH_SHORT).show()
            }
        }
        if (fragment != null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()

            item.setChecked(true)
            getSupportActionBar()?.setTitle(item.getTitle());
        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }
}