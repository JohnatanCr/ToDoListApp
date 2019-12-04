package com.modelo.todolistapp.UI


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.modelo.todolistapp.NavigationDrawerActivity

import com.modelo.todolistapp.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_navigation_drawer.*


/**
 * A simple [Fragment] subclass.
 */
class NewListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_new_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn : Button = view.findViewById(R.id.button)
        btn.setOnClickListener{

            NavigationDrawerActivity().addMenuItem()

        }

    }




    // funcion para crear un fragment
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

}
