package com.modelo.todolistapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.modelo.todolistapp.R
import yuku.ambilwarna.AmbilWarnaDialog

class CreateSharedList : AppCompatActivity() {

    private lateinit var btn_color : Button

    private var defaultColor : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shared_list)

        btn_color = findViewById(R.id.btn_color)

        btn_color.setOnClickListener { openColorPicker() }

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
}
