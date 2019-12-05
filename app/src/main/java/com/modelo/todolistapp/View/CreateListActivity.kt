package com.modelo.todolistapp.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.modelo.todolistapp.Class.LocalList
import com.modelo.todolistapp.Class.SharedPreference
import com.modelo.todolistapp.Fragments.AllTasksFragment
import com.modelo.todolistapp.R
import yuku.ambilwarna.AmbilWarnaDialog

class CreateListActivity : AppCompatActivity() {


    private lateinit var tv_ListName : EditText
    private lateinit var btn_color : Button
    private lateinit var rg_iconos : RadioGroup
    private lateinit var btn_save : Button
    private lateinit var btn_cancel : Button
    private val database = FirebaseDatabase.getInstance()
    private var defaultColor : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_list)
        val sharedPreference = SharedPreference(this)

        tv_ListName = findViewById(R.id.list_edtiText_nameList)
        btn_color = findViewById(R.id.btn_color)
        rg_iconos = findViewById(R.id.icons_radio_group)
        btn_save = findViewById(R.id.list_button_crearLista)
        btn_cancel = findViewById(R.id.list_button_cancelarLista)

        var userId : String = sharedPreference.getValueString("email")!!
        userId = encodeUserEmail(userId)


        btn_color.setOnClickListener { openColorPicker() }


        //Guardar la lista creada

        btn_save.setOnClickListener{
            val lista = LocalList(
                title = tv_ListName.text.toString().trim(),
                backgroundColor = defaultColor.toString().trim(),
                listIcon = rg_iconos.checkedRadioButtonId,
                idUser = userId.trim(),
                userName = "username",
                idList = tv_ListName.text.toString().trim())


            //TODO: crear funcion para validar lista
            if(!validarLista(lista))
            {
                //TODO: identificar al usuario loggeado para añadir al path
                val listRef = database.getReference("app").child("users").child(userId).child("listasLocales").child(lista.idList)

                listRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                            listRef.setValue(lista)
                            Toast.makeText(
                                this@CreateListActivity,
                                "EXITO",
                                Toast.LENGTH_LONG
                            ).show()
                    }
                })

               // val entry:LocalList? = LocalList()
                val intent = Intent().apply { putExtra("newList", lista) }
                setResult(Activity.RESULT_OK, intent)
                finish()

            }
            else{
                Toast.makeText(this, "Verifique Sus Campos", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun validarLista(lista : LocalList): Boolean{
        return if(tv_ListName.text.isNotBlank() && rg_iconos.checkedRadioButtonId != -1){
            false
        }else
            return true
    }

    fun openColorPicker(){
        var colorPicker = AmbilWarnaDialog(this, defaultColor, object: AmbilWarnaDialog.OnAmbilWarnaListener{
            override fun onCancel(dialog: AmbilWarnaDialog?) {
            }
            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                defaultColor = color
                btn_color.setBackgroundColor(defaultColor)
            }
        })
        colorPicker.show()
    }
    private fun encodeUserEmail(userEmail: String): String {
        return userEmail.replace(".", ",")
    }
}
