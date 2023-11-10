package com.example.parcialgrupo3whale.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.parcialgrupo3whale.R
import com.example.parcialgrupo3whale.adapters.PetsListHomeAdapter
import com.example.parcialgrupo3whale.database.dao.PetDao
import com.example.parcialgrupo3whale.database.dao.WhaleDatabase
import com.example.parcialgrupo3whale.database.entities.PetEntity
import com.example.parcialgrupo3whale.databinding.ActivityMainBinding
import com.example.parcialgrupo3whale.enums.Location
import com.example.parcialgrupo3whale.gateway.model.PetRandomImageResponse
import com.example.parcialgrupo3whale.gateway.service.PetAPIService
import com.example.parcialgrupo3whale.gateway.service.ServicePetApiBuilder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var btmNavView: BottomNavigationView
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<PetEntity>
    private lateinit var petsAdapter: PetsListHomeAdapter
    private lateinit var navController: NavController
    private var db : WhaleDatabase? = null
    private var petDao: PetDao? = null
    private lateinit var petApiService : PetAPIService
    private var userName : String? = null
    private var dbName : String? = "petDB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración inicial de la base de datos y API
        db = WhaleDatabase.getWhaleDatabase(this)
        petDao = db?.petDao()
        petApiService = ServicePetApiBuilder.create()

        // Población inicial de la base de datos
        populateDatabase()

        // Configuración del usuario y la navegación
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
        val bundle = Bundle()
        bundle.putString("userName", userName)
        bundle.putString("databaseName", dbName)
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
                    navController.navigate(R.id.nav_drawer_profile, bundle)
                    drawerLayout.closeDrawers() // Cierra el DrawerLayout después de seleccionar el ítem
                    setToolbarTitle("Profile")
                }
                R.id.nav_drawer_configuration -> {
                    navController.navigate(R.id.action_global_nav_drawer_configuration)
                    setToolbarTitle("Configuration")
                }
                else -> {
                    supportActionBar?.show()
                    btmNavView.visibility = View.VISIBLE
                }
            }
            drawerLayout.closeDrawers()  // Cierra el DrawerLayout después de seleccionar el ítem
            true
        }

        btmNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment, bundle)
                    true
                }
                R.id.nav_favourites -> {
                    navController.navigate(R.id.favouriteFragment, bundle)
                    true
                }
                R.id.nav_adoption_form -> {
                    navController.navigate(R.id.adoptionsFragment, bundle)
                    true
                }
                R.id.nav_adoptions -> {
                    navController.navigate(R.id.adoptionFormFragment, bundle)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    btmNavView.menu.findItem(R.id.nav_home).isChecked = true
                    setToolbarTitle("Home")
                }
                R.id.favouriteFragment -> {
                    btmNavView.menu.findItem(R.id.nav_favourites).isChecked = true
                    setToolbarTitle("Favourite")
                }
                R.id.adoptionFormFragment -> {
                    btmNavView.menu.findItem(R.id.nav_adoptions).isChecked = true
                    setToolbarTitle("Adoption Form")
                }
                R.id.adoptionsFragment -> {
                    btmNavView.menu.findItem(R.id.nav_adoption_form).isChecked = true
                    setToolbarTitle("Adoptions")
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

    private fun getRandomImageUrl(): String {
        val call: Call<PetRandomImageResponse> = petApiService.getRandomImage()
        var url : String = ""

        call.enqueue(object : Callback<PetRandomImageResponse> {
            override fun onResponse(call: Call<PetRandomImageResponse>, response: Response<PetRandomImageResponse>) {
                if (response.isSuccessful) {
                    val petRandomImageResponse: PetRandomImageResponse? = response.body()
                    val imageUrl = petRandomImageResponse?.message ?: ""
                    url = imageUrl
                }
            }

            override fun onFailure(call: Call<PetRandomImageResponse>, t: Throwable) {
                // Manejar errores de la red
            }
        })
        return url
    }

    private fun populateDatabase() {
        var initialPets = ArrayList<PetEntity>()

        initialPets.add(PetEntity(1, "Luna", "10", "15", "", false, Location.CABA, "Lautaro", getRandomImageUrl(), "calle", ""))
        initialPets.add(PetEntity(2, "Tatu", "12", "20", "", true, Location.CABA, "Mateo", getRandomImageUrl(), "pitbull", ""))
        initialPets.add(PetEntity(3, "Buddy", "8", "10", "", true, Location.MENDOZA, "Juan", getRandomImageUrl(), "golden", ""))
        initialPets.add(PetEntity(4, "Roma", "5", "11", "", false, Location.CABA, "Ariel", getRandomImageUrl(), "chihuahua", ""))
        initialPets.add(PetEntity(5, "Cuqui", "2", "14", "", false, Location.TUCUMAN, "Ursula", getRandomImageUrl(), "calle", ""))
        initialPets.add(PetEntity(6, "Paul", "3", "12", "", true, Location.CABA, "Pedro", getRandomImageUrl(), "golden", ""))
        initialPets.add(PetEntity(7, "Pancho", "4", "10", "", true, Location.CORDOBA, "Lara", getRandomImageUrl(), "terrier", ""))
        initialPets.add(PetEntity(8, "Ulises", "5", "8", "", true, Location.CABA, "Ignacio", getRandomImageUrl(), "salchicha", ""))
        initialPets.add(PetEntity(9, "Rocco", "7", "19", "", true, Location.CORDOBA, "Jorge", getRandomImageUrl(), "salchicha", "", true))
        initialPets.add(PetEntity(10, "Tobby", "2", "18", "", true, Location.MENDOZA, "Matias", getRandomImageUrl(), "terrier", ""))

        petDao?.insertAllPets(initialPets)
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

}
