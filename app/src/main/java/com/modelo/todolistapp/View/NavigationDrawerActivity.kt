package com.modelo.todolistapp.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.modelo.todolistapp.Class.DataBaseFireBase
import com.modelo.todolistapp.Class.LocalList
import com.modelo.todolistapp.Class.SharedPreference
import com.modelo.todolistapp.Class.User
import com.modelo.todolistapp.Fragments.AllTasksFragment
import com.modelo.todolistapp.Fragments.ImportantTasksFragment
import com.modelo.todolistapp.R
import com.modelo.todolistapp.UI.NewListFragment
import kotlinx.android.synthetic.main.activity_navigation_drawer.*
import kotlinx.android.synthetic.main.app_bar_nd.*

class NavigationDrawerActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val database = DataBaseFireBase()
    lateinit var allfragment: Fragment
    lateinit var newlistfragment: NewListFragment

    private lateinit var tvUserName: TextView
    private lateinit var tvUserMail: TextView
    private lateinit var iv_UserIcon: ImageView

    companion object {
        val REQUEST_CODE_NEW_ENTRY = 1
        lateinit var sharedPreference: SharedPreference
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        sharedPreference = SharedPreference(this)
        var currentUser: User = SharedPreference(this).getValueUser("usuario")


        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        val drawerToggle = object : ActionBarDrawerToggle(
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

        nav_view.setNavigationItemSelectedListener(this)


        allfragment = AllTasksFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, allfragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val headerLayout = nav_view.getHeaderView(0)

        tvUserName = headerLayout.findViewById(R.id.tv_username)
        tvUserMail = headerLayout.findViewById(R.id.tv_mail)
        iv_UserIcon = headerLayout.findViewById(R.id.iv_userImage)

        tvUserName.text = currentUser.name
        tvUserMail.text = decodeUserEmail(currentUser.email)
        iv_UserIcon.setImageResource(currentUser.icon)
        //addMenuItemInNavMenuDrawer()
        addListsOfUser(currentUser)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean{
        when (menuItem.itemId) {
            /*
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
             */
            R.id.nuevaLista -> {

                val i = Intent(this, CreateListActivity::class.java)
                startActivityForResult(i, REQUEST_CODE_NEW_ENTRY)
            }

            R.id.nuevaListaCompartida -> {
                val intent = Intent(this, CreateSharedList::class.java)
                startActivity(intent)
            }
            R.id.close_session -> {
                sharedPreference.clearSharedPreference()
                val i = Intent(this, LoginActivity::class.java)
                startActivityForResult(i, REQUEST_CODE_NEW_ENTRY)
                finish()
            }

            else -> {
                allfragment = ImportantTasksFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, allfragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_NEW_ENTRY) {
            if (resultCode == Activity.RESULT_OK) {
                val entry: LocalList? = data?.getParcelableExtra("newList")
                //LLAMADA A FUNCION
                addMenuItem(entry)
            }
        }
    }

    //AHORA RECIBE LA LOCAL LIST NO SOLO UN STRING
    fun addMenuItem(localList: LocalList?) {
        try {
            var navView: NavigationView = nav_view
            var menu = navView.menu
            menu.add(
                R.id.groupLists,
                Menu.NONE,
                1,
                localList!!.title
            )//.setIcon(localList!!.listIcon)
        } catch (e: Exception) {
            Log.println(taskId, "error", e.localizedMessage)
        }
    }

    fun addListsOfUser(user: User) {
        val listReference = database.getListReference(user)
        listReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {

                    for (value in p0.children) {
                        addMenuItem(value.getValue(LocalList::class.java))
/*                        Toast.makeText(
                            this@NavigationDrawerActivity,
                            value.key.toString(),
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                }
            }
        })
    }

    private fun decodeUserEmail(userEmail: String): String {
        return userEmail.replace(",", ".")
    }

}
