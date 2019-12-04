package com.modelo.todolistapp.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.modelo.todolistapp.R

data class Tarea(var nombretarea : String = "", var descripcion : String = "", var importancia : String = "", var fechavenc : String = "", var nameexterno : String = "") {
    var id: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tarea

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}

/*
class DemoAdapter(private var tareas : ArrayList<Tarea>) : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class DemoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var tvNombresito: TextView
        private var tvdate: TextView
        private var check: CheckBox
        private var nombresocio: TextView
        private var imagen : ImageView

        init {
            tvNombresito = view.findViewById(R.id.textname)
            tvdate = view.findViewById(R.id.idFecha)
            check = view.findViewById(R.id.idcheck)
            nombresocio = view.findViewById(R.id.idExterno)
            imagen = view.findViewById(R.id.imagensitaid)
        }
    }
}

 */

class editorTareasActivity : AppCompatActivity() {


    private lateinit var tvName: TextView
    private lateinit var tvDesc: TextView
    private lateinit var difBaja: RadioButton
    private lateinit var difMedia: RadioButton
    private lateinit var difAlta: RadioButton
    private lateinit var tvFechaVencimiento: TextView
    private lateinit var btnAgregar: Button

    private lateinit var rv: RecyclerView

    private var tareas: ArrayList<Tarea> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editortareas)

        tvName = findViewById(R.id.idname)
        tvDesc = findViewById(R.id.iddesc)
        difBaja = findViewById(R.id.r1)
        difMedia = findViewById(R.id.r2)
        difAlta = findViewById(R.id.r3)
        tvFechaVencimiento = findViewById(R.id.idexpire)
        btnAgregar = findViewById(R.id.btnAdd)



        btnAgregar.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val tareanombreRef = database.getReference("app").child("users")
            if (tvName.text.toString() == null) {
                Toast.makeText(this, "No has agregado la tarea!", Toast.LENGTH_SHORT)
            } else {

                val tarea = Tarea(
                    tvName.text.toString().trim(),
                    tvDesc.text.toString().trim(),
                    tvFechaVencimiento.text.toString().trim()
                )
                tareanombreRef.push().setValue(tarea)

                //Desactiva botones
                if (difBaja.isChecked || difMedia.isChecked || difAlta.isChecked) {
                    difBaja.isChecked = false
                    difMedia.isChecked = false
                    difAlta.isChecked = false
                }
                tvName.setText("")
                tvDesc.setText("")
                tvFechaVencimiento.setText("")

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("app").child("users")
        usersRef.addChildEventListener(object : ChildEventListener {
            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val tarea = p0.getValue(Tarea::class.java)
                tarea?.id = p0.key

                //var nombretarea : String = "", var descripcion : String = "", var importancia : String = "", var fechavenc : String = "", var nameexterno : String = ""

                val TareaActual: Tarea = tareas.get(tareas.indexOf(tarea))
                TareaActual.nombretarea = tarea!!.nombretarea
                TareaActual.descripcion = tarea!!.descripcion
                TareaActual.fechavenc = tarea!!.fechavenc
                TareaActual.nameexterno = tarea!!.nameexterno

                rv.adapter?.notifyDataSetChanged()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val tarea = p0.getValue(Tarea::class.java)
                tarea?.id = p0.key

                tareas.add(tarea!!)
                rv.adapter?.notifyDataSetChanged()
            }
        })


    }
}
