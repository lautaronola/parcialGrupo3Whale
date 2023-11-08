package com.example.parcialgrupo3whale.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var btmNavView: BottomNavigationView
    private lateinit var navController: NavController
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userName = intent.getStringExtra("userName")
        setUpToolbar()
        setUpNavigation()
    }

    private fun setUpToolbar() {
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START) // Abre el DrawerLayout al tocar el botón de hamburguesa
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout = binding.drawerLayout
        navView = binding.navViewMain
        btmNavView = binding.btmNavViewMain
        navView.inflateMenu(R.menu.drawer_menu)

        // Configurar ActionBarDrawerToggle para el cajón de navegación
        val toggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar la AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.favouriteFragment,
            R.id.adoptionFormFragment,
            R.id.adoptionsFragment
        )
            .setDrawerLayout(drawerLayout)
            .build()

        // Configurar la barra de herramientas y la navegación con NavigationUI
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupWithNavController(btmNavView, navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_drawer_profile -> {
                    val bundle = Bundle()
                    bundle.putString("userName", userName)
                    navController.navigate(R.id.nav_drawer_profile, bundle)
                    drawerLayout.closeDrawers() // Cierra el DrawerLayout después de seleccionar el ítem
                    true
                }
                R.id.nav_drawer_configuration -> {
                    navController.navigate(R.id.nav_drawer_configuration)
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }
        btmNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.nav_favourites -> {
                    navController.navigate(R.id.favouriteFragment)
                    true
                }
                R.id.nav_adoption_form -> {
                    navController.navigate(R.id.adoptionsFragment)
                    true
                }
                R.id.nav_adoptions -> {
                    navController.navigate(R.id.adoptionFormFragment)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    btmNavView.menu.findItem(R.id.nav_home).isChecked = true
                }
                R.id.favouriteFragment -> {
                    btmNavView.menu.findItem(R.id.nav_favourites).isChecked = true
                }
                R.id.adoptionFormFragment -> {
                    btmNavView.menu.findItem(R.id.nav_adoptions).isChecked = true
                }
                R.id.adoptionsFragment -> {
                    btmNavView.menu.findItem(R.id.nav_adoption_form).isChecked = true
                }
            }
        }
    }

    // lo llamamos desde setting fr y profile fr, para manejar visibilidad del navbar
    fun setBottomNavViewVisibility(visibility: Int) {
        if (btmNavView != null) {
            btmNavView.setVisibility(visibility)
        }
    }
}
