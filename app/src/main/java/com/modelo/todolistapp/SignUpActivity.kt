package com.modelo.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val editText_emailRegister: TextInputEditText = findViewById(R.id.editText_emailRegister)
        val editText_userNameRegister: TextInputEditText =
            findViewById(R.id.editText_userNameRegister)
        val editText_passwordRegister: TextInputEditText =
            findViewById(R.id.editText_passwordRegister)
        val editText_confirmPasswordRegister: TextInputEditText =
            findViewById(R.id.editText_confirmPasswordRegister)
        val button_createAccount: Button = findViewById(R.id.button_createAccount)
        val button_toLogin: Button = findViewById(R.id.button_toLogin)

        button_createAccount.setOnClickListener {
            val intent = Intent(this, NavigationDrawerActivity::class.java)
            startActivity(intent)
        }
    }
}
