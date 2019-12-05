package com.modelo.todolistapp.View

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.modelo.todolistapp.Class.DataBaseFireBase
import com.modelo.todolistapp.Class.User
import com.modelo.todolistapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private val database = DataBaseFireBase()

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
        val textInputLayoutEmailRegister: TextInputLayout =
            findViewById(R.id.textInputLayout_emailRegister)
        val textInputLayoutUserName: TextInputLayout =
            findViewById(R.id.textInputLayout_userNameRegister)
        val textInputLayoutPasswordRegister: TextInputLayout =
            findViewById(R.id.textInputLayout_passwordRegister)
        val textInputLayoutConfirmPasswordRegister: TextInputLayout =
            findViewById(R.id.textInputLayout_confirmPasswordRegister)

        button_createAccount.setOnClickListener {

            if (validateEmail() && validateUserName() && validatePassword() && validateConfirmPassword()) {
                val user = User(
                    encodeUserEmail(editText_emailRegister.text.toString().trim()),
                    encodeUserEmail(editText_emailRegister.text.toString().trim()),
                    editText_userNameRegister.text.toString().trim(),
                    editText_passwordRegister.text.toString(),
                    1234,
                    false
                )
                val usersRef = database.getUsersReference().child(user.idUser)

                usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.value == null) {
                            usersRef.setValue(user)
                            Toast.makeText(
                                this@SignUpActivity,
                                "EXITO",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                "Ya existe el usuario",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            }

        }

        button_toLogin.setOnClickListener {
            onBackPressed()
        }

        editText_emailRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateEmail()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        editText_userNameRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                validateUserName()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        editText_passwordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
                validateConfirmPassword()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        editText_confirmPasswordRegister.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateConfirmPassword()
            }
        })


    }




    private fun validateEmail(): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(editText_emailRegister.text?.trim()).matches() || editText_emailRegister.text.isNullOrEmpty()) {
            textInputLayout_emailRegister.error = "Ingrese un Correo Valido"
            false
        } else {
            textInputLayout_emailRegister.error = null
            true
        }
    }

    private fun validateUserName(): Boolean {
        return if (editText_userNameRegister.text.isNullOrBlank()) {
            textInputLayout_userNameRegister.error = "Ingrese un nombre de Usuario"
            false

        } else {
            textInputLayout_userNameRegister.error = null
            true
        }

    }

    private fun validatePassword(): Boolean {

        return when {
            editText_passwordRegister.text!!.length < 8 -> {
                textInputLayout_passwordRegister.error = "Minimo 8 Caracteres"
                false
            }
            editText_passwordRegister.text!!.contains(" ") -> {
                textInputLayout_passwordRegister.error = "No debe de haber espacios en blanco"
                false
            }
            else -> {
                textInputLayout_passwordRegister.error = null
                textInputLayout_passwordRegister.helperText = null
                true
            }
        }

    }

    private fun validateConfirmPassword(): Boolean {
        var password = editText_passwordRegister.text.toString()
        var confirmPassword = editText_confirmPasswordRegister.text.toString()

        return if (password != confirmPassword || confirmPassword.length < 8) {
            textInputLayout_confirmPasswordRegister.error = "Las contraseñas no coinciden"
            false
        } else {
            textInputLayout_confirmPasswordRegister.error = null
            true
        }
    }

    private fun encodeUserEmail(userEmail: String): String {
        return userEmail.replace(".", ",")
    }
}

