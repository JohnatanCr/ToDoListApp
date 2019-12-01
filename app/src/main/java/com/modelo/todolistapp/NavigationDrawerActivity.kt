package com.modelo.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_nd.*

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var allfragment : AllTasksFragment
    lateinit var importatfragment : ImportantTasksFragment
    lateinit var plannedfragment : PlannedTasksFragment
    lateinit var pendingfragment : PendingTasksFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        val drawerToggle = object  : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolBar,
            (R.string.open),
            (R.string.close)
        ) {

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener (this)

        allfragment = AllTasksFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, allfragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.todas -> {
                allfragment = AllTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.importantes -> {
                importatfragment = ImportantTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, importatfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.planeadas -> {
                plannedfragment = PlannedTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, plannedfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.pendientes -> {
                pendingfragment = PendingTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, pendingfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            }
        else{
            super.onBackPressed()
        }
    }
}
