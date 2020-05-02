package com.jojartbence.utdijkalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val topLevelDestinations = setOf(R.id.movementListFragment, R.id.depositFragment)
        appBarConfig = AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawer_layout).build()


        navController = Navigation.findNavController(this, R.id.fragment)
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)

    }
}
