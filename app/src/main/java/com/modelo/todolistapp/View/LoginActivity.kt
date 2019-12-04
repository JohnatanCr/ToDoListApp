package com.modelo.todolistapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.modelo.todolistapp.R
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button_toRegister : Button = findViewById(R.id.button_toRegister)

        button_toRegister.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onBackPressed() {
        exitProcess(-1)
    }
}
