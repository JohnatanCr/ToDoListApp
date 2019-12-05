package com.modelo.todolistapp.Class

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DataBaseFireBase {

    private companion object ConnectionFireBase{
        private var database : FirebaseDatabase = FirebaseDatabase.getInstance()
        init {
            database.setPersistenceEnabled(true)
        }
    }

     fun getUsersReference() : DatabaseReference{
        return database.getReference("app").child("users")
    }

    fun getListReference() :DatabaseReference{
        return database.getReference("app").child("lists")
    }
    fun getParticulaUserReference(idUser : String) : DatabaseReference{
        return getUsersReference().child(idUser)
    }

}