package com.example.locationsharingapp.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.locationsharingapp.R
import com.example.locationsharingapp.databinding.ActivityMainBinding
import com.example.locationsharingapp.view.LoginActivity // Corrected path to LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var actionDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize NavController for fragment navigation
        val navController = findNavController(R.id.fragmentContainerView)

        // Set up bottom navigation and drawer navigation with NavController
        binding.bottomBar.setupWithNavController(navController)
        binding.drawerNav.setupWithNavController(navController)

        // Set up drawer toggle for opening/closing the drawer
        actionDrawerToggle = ActionBarDrawerToggle(
            this, binding.drawerlayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerlayout.addDrawerListener(actionDrawerToggle)
        actionDrawerToggle.syncState()

        // Enable action bar button to toggle drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle drawer navigation item selection
        binding.drawerNav.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    // Handle logout action
                    Firebase.auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java)) // Correct path to LoginActivity
                    finish()
                }
                R.id.profileFragment2 -> {
                    // Navigate to profile fragment
                    navController.navigate(R.id.profileFragment2)
                }
                R.id.friendsFragment2 -> {
                    // Navigate to friends fragment
                    navController.navigate(R.id.friendsFragment2)
                }
            }
            // Close drawer after item selection
            binding.drawerlayout.closeDrawers()
            true
        }

        // Handle bottom navigation item selection
        binding.bottomBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileFragment2 -> {
                    navController.navigate(R.id.profileFragment2)
                }
                R.id.friendsFragment2 -> {
                    navController.navigate(R.id.friendsFragment2)
                }

                R.id.logout -> {
                    // Handle logout action
                    Firebase.auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java)) // Correct path to LoginActivity
                    finish()
                }
            }
            true
        }
    }

    // Handle drawer toggle action on options item selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
