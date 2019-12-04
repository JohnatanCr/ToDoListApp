package com.modelo.todolistapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
<<<<<<< Updated upstream:app/src/main/java/com/modelo/todolistapp/View/NavigationDrawerActivity.kt
import com.modelo.todolistapp.Fragments.AllTasksFragment
import com.modelo.todolistapp.Fragments.ImportantTasksFragment
import com.modelo.todolistapp.Fragments.PendingTasksFragment
import com.modelo.todolistapp.Fragments.PlannedTasksFragment
import com.modelo.todolistapp.R
=======
import com.modelo.todolistapp.UI.*
import kotlinx.android.synthetic.*
>>>>>>> Stashed changes:app/src/main/java/com/modelo/todolistapp/NavigationDrawerActivity.kt
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_nd.*

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var allfragment : AllTasksFragment
    lateinit var newlistfragment : NewListFragment

    private lateinit var tvUserName: TextView
    private lateinit var tvUserMail: TextView



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

        val headerLayout = nav_view.getHeaderView(0)

        tvUserName=headerLayout.findViewById(R.id.tv_username)
        tvUserMail=headerLayout.findViewById(R.id.tv_mail)

        //addMenuItemInNavMenuDrawer()
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
                allfragment = AllTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.planeadas -> {
                allfragment = AllTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.pendientes -> {
                allfragment = AllTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nuevaLista -> {
                newlistfragment = NewListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, newlistfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nuevaListaCompartida -> {
                allfragment = AllTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            menuItem.itemId ->{

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

    fun addMenuItem() {

        var navView : NavigationView = nav_view
        var menu = navView.menu
        menu.add(0,1,0,"Nombre de lista")

        navView.invalidate()
    }

}
