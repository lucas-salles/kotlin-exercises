package com.example.minhastarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvTarefas: ListView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: ArrayList<Tarefa>
    private lateinit var tarefaDAO: TarefaDAO
    val FORM = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tarefaDAO = TarefaDAO(this)
        this.lista = tarefaDAO.readAll()

        this.lvTarefas = findViewById(R.id.lvMainTarefas)
        this.fabAdd = findViewById(R.id.fabMainAdd)

        this.lvTarefas.adapter = TarefaAdapter(this, this.lista)

        this.fabAdd.setOnClickListener { add(it) }

        this.lvTarefas.setOnItemClickListener( ClickCurto() )
        this.lvTarefas.setOnItemLongClickListener( ClickLongo() )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == FORM) {
            this.atualiza()
        }
    }

    private fun atualiza() {
        this.lista.clear()
        this.lista.addAll(tarefaDAO.readAll())
        (this.lvTarefas.adapter as BaseAdapter).notifyDataSetChanged()
    }

    private fun add(view: View) {
        val intent = Intent(this, FormActivity::class.java)
        startActivityForResult(intent, FORM)
    }

    inner class ClickCurto: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            val tarefa = this@MainActivity.lvTarefas.adapter.getItem(position) as Tarefa
            intent.putExtra("TAREFA", tarefa)
            startActivityForResult(intent, FORM)
        }
    }

    inner class ClickLongo: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            val tarefa = this@MainActivity.lvTarefas.adapter.getItem(position) as Tarefa

            val janela = AlertDialog.Builder(this@MainActivity)
            janela.setTitle("Confirmar")
            janela.setIcon(R.mipmap.ic_launcher)
            janela.setMessage("Deseja Excluir?")

            janela.setPositiveButton("Excluir") {dialog, which ->
                this@MainActivity.tarefaDAO.delete(tarefa.id)
                this@MainActivity.atualiza()
            }

            janela.setNegativeButton("Cancelar") {dialog, which ->
                Toast.makeText(this@MainActivity, "Cancelado", Toast.LENGTH_SHORT).show()
            }

            janela.create().show()

            return true
        }
    }
}