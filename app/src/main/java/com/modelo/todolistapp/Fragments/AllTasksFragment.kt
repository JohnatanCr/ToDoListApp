package com.modelo.todolistapp.Fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.LocaleList
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.modelo.todolistapp.Class.LocalList
import com.modelo.todolistapp.R
import kotlinx.android.synthetic.main.activity_navigation_drawer.*

/**
 * A simple [Fragment] subclass.
 */
class AllTasksFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_all_tasks, container, false)
    }

}



