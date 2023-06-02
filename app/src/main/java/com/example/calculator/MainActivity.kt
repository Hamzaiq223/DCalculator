package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.calculator.Fragments.BasicCalculator
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var frameLayout: FrameLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer


        drawerLayout = findViewById(R.id.my_drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        frameLayout = findViewById(R.id.fragment_container)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }

        // Load the default fragment on app launch
        val defaultFragment = BasicCalculator()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, defaultFragment).commit()

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun selectDrawerItem(menuItem: MenuItem) {
        // Load the appropriate fragment into the frame layout based on the menu item selected
        when (menuItem.itemId) {
            R.id.nav_basic -> {

                val fragment = BasicCalculator()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
                supportActionBar?.title = "Basic Calculator"
            }

        }

        // Highlight the selected item in the navigation menu and close the drawer
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        }
        else super.onOptionsItemSelected(item)
    }
}