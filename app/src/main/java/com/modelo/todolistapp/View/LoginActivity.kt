package com.modelo.todolistapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.modelo.todolistapp.Class.DataBaseFireBase
import com.modelo.todolistapp.Class.SharedPreference
import com.modelo.todolistapp.Class.User
import com.modelo.todolistapp.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    private val database = DataBaseFireBase()
    var canLogIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference: SharedPreference =SharedPreference(this)

        val editText_emailLogin: TextInputEditText = findViewById(R.id.editText_emailLogin)
        val editText_passwordLogin: TextInputEditText = findViewById(R.id.editText_passwordLogin)
        val textInputLayout_emailLogin: TextInputLayout =
            findViewById(R.id.textInputLayout_emailLogin)
        val textInputLayout_passwordLogin: TextInputLayout =
            findViewById(R.id.textInputLayout_passwordLogin)

        val button_toRegister: Button = findViewById(R.id.button_toRegister)

        button_toRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val button_loggInUser: Button = findViewById(R.id.button_loginAccount)

        button_loggInUser.setOnClickListener {
            if (validateEmail() && validatePassword()){
                    var emailId = encodeUserEmail(editText_emailLogin.text.toString().trim())
                    val usersRef = database.getUsersReference().child(emailId)

                    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            if (p0.value != null) {
                                var userGet = p0.getValue(User::class.java)
                                if (userGet!!.password == editText_passwordLogin.text.toString().trim()) {
                                    canLogIn = userGet!!.verified
                                    if(canLogIn){
                                        sharedPreference.save("email", emailId)
                                        sharedPreference.save("isLogged", true)

                                        startActivity(Intent(this@LoginActivity, NavigationDrawerActivity::class.java))
                                    }
                                    else{
                                        Toast.makeText(this@LoginActivity, "Cuenta no autorizada", Toast.LENGTH_LONG).show()
                                    }

                                } else {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "La Contraseña Es Incorrecto",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "El usuario No Existe",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    })
            }
            else {
                Toast.makeText(this, "Verifique Sus Campos", Toast.LENGTH_LONG)
            }
/*
            if (canLogIn) {

                startActivity(Intent(this, NavigationDrawerActivity::class.java))
            }
            else{
                Toast.makeText(this@LoginActivity, "Cuenta no autorizada", Toast.LENGTH_LONG).show()
            }*/
        }

    }

    override fun onBackPressed() {
        finish()
    }

    private fun validateEmail(): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(editText_emailLogin.text?.trim()).matches() || editText_emailLogin.text.isNullOrEmpty()) {
            textInputLayout_emailLogin.error = "Ingrese un Correo Valido"
            false
        } else {
            textInputLayout_emailLogin.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return when {
            editText_passwordLogin.text!!.length < 8 -> {
                textInputLayout_passwordLogin.error = "Minimo 8 Caracteres"
                false
            }
            editText_passwordLogin.text!!.contains(" ") -> {
                textInputLayout_passwordLogin.error = "No debe de haber espacios en blanco"
                false
            }
            else -> {
                textInputLayout_passwordLogin.error = null
                textInputLayout_passwordLogin.helperText = null
                true
            }
        }
    }

    private fun encodeUserEmail(userEmail: String): String {
        return userEmail.replace(".", ",")
    }

}
