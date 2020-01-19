package com.doilio.shopifymemory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.doilio.shopifymemory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.myNavHostFragment)
        drawerLayout = binding.drawerLayout

        setupActionBarWithNavController(navController, drawerLayout)
        setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { controller, destination, _ ->

            when (destination.id) {
                controller.graph.startDestination -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                else -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, drawerLayout)
    }
}
