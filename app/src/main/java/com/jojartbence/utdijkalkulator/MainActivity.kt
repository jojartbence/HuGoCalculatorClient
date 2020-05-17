package com.jojartbence.utdijkalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfig: AppBarConfiguration
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val topLevelDestinations = setOf(R.id.truckListFragment, R.id.depositFragment, R.id.serverUrlSetFragment)
        appBarConfig = AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawer_layout).build()


        navController = Navigation.findNavController(this, R.id.fragment)
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        navController.popBackStack()
        navController.navigate(item.itemId)
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        return true
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
