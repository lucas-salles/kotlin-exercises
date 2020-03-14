package com.example.deuruim

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnNovo: Button
    private lateinit var tvQuantidade: TextView

    private lateinit var cadastro: Cadastro

    val FORM = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.cadastro = Cadastro()

        this.btnNovo = findViewById(R.id.btnMainNovo)
        this.tvQuantidade = findViewById(R.id.tvMainQuantidade)

        this.tvQuantidade.text = this.cadastro.count().toString()

        this.btnNovo.setOnClickListener(OnClickBotao())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == FORM) {
            val evento = data?.getParcelableExtra<Evento>("EVENTO")
            Log.i("APP_DEURUIM", evento.toString())

            this.cadastro.add(evento!!)

            this.tvQuantidade.text = this.cadastro.count().toString()
            Log.i("APP_DEURUIM", this.cadastro.count().toString())
        }
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val intent = Intent(this@MainActivity, FormActivity::class.java)
            startActivityForResult(intent, FORM)
        }
    }
}
