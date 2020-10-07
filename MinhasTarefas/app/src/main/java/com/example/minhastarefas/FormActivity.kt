package com.example.minhastarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class FormActivity : AppCompatActivity() {
    private lateinit var etDescricao: EditText
    private lateinit var tvPrioridade: TextView
    private lateinit var skPrioridade: SeekBar
    private lateinit var llStatus: LinearLayout
    private lateinit var etStatus: EditText
    private lateinit var btnCancelar: Button
    private lateinit var btnSalvar: Button
    private lateinit var tarefaDAO: TarefaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.tarefaDAO = TarefaDAO(this)

        this.etDescricao = findViewById(R.id.etFormDescricao)
        this.tvPrioridade = findViewById(R.id.tvFormPrioridade)
        this.skPrioridade = findViewById(R.id.skFormPrioridade)
        this.etStatus = findViewById(R.id.etFormStatus)
        this.llStatus = findViewById(R.id.llFormStatus)
        this.btnCancelar = findViewById(R.id.btnFormCancelar)
        this.btnSalvar = findViewById(R.id.btnFormSalvar)

        this.btnCancelar.setOnClickListener { cancelar(it) }
        this.btnSalvar.setOnClickListener { salvar(it) }

        this.skPrioridade.setOnSeekBarChangeListener(ChangeSeekBar())

        if (intent.hasExtra("TAREFA")) {
            this.llStatus.visibility = View.VISIBLE
            val tarefa = intent.getSerializableExtra("TAREFA") as Tarefa
            this.etDescricao.setText(tarefa.descricao)
            this.skPrioridade.progress = tarefa.prioridade
            this.etStatus.setText(tarefa.status)
        }
    }

    inner class ChangeSeekBar: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this@FormActivity.tvPrioridade.text = progress.toString()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    private fun cancelar(view: View){
        finish()
    }

    private fun salvar(view: View){
        val descricao = this.etDescricao.text.toString()
        val prioridade = this.skPrioridade.progress
        val status = this.etStatus.text.toString()

        if (intent.hasExtra("TAREFA")) {
            val tarefa = intent.getSerializableExtra("TAREFA") as Tarefa
            val id = tarefa.id
            this.tarefaDAO.update(Tarefa(id, descricao, prioridade, status))
        } else {
            val tarefa = Tarefa(descricao, prioridade, "Aberto")
            this.tarefaDAO.create(tarefa)
        }

        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}