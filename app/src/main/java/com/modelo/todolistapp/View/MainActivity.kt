package com.modelo.todolistapp.View


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.modelo.todolistapp.R


class DemoAdapter(private val tareas: ArrayList<Tarea>) : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class DemoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var tvName: TextView
        private var tvDate: TextView
        private var checkboxito : CheckBox
        private var tvExternoName : TextView
        //private var btnDelete:Button

        init {
            tvName = view.findViewById(R.id.textname)
            tvDate = view.findViewById(R.id.idFecha)
            checkboxito = view.findViewById(R.id.idcheck)
            tvExternoName = view.findViewById(R.id.idExterno)
            //btnDelete=view.findViewById(R.id.delete_button)
        }

        public fun bind(tarea: Tarea) {
            tvName.setText("${tarea.nombretarea}")
            tvDate.setText("${tarea.fechavenc}")
            tvExternoName.setText(("${tarea.nameexterno}"))


            checkboxito.setOnClickListener {
                val database = FirebaseDatabase.getInstance()
                val usersRef = database.getReference("app").child("Tareas")
                usersRef.child(tarea.id.toString()).removeValue()
                Snackbar.make(it, "Tarea BORRADA!", Snackbar.LENGTH_INDEFINITE).setAction("Deshacer",
                    {
                        Snackbar.make(it, "Tarea recuperada!", Snackbar.LENGTH_LONG).show()
                    })
                    .show() //LOCAL
            }
        }
    }
}



class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private var tareas: ArrayList<Tarea> = arrayListOf()
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this, editorTareasActivity::class.java)
            startActivity(intent)

            rv = findViewById<RecyclerView>(R.id.rv).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = DemoAdapter(tareas)
            }


        }
    }
}