package com.example.arrocheonumero

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvIntervalo: TextView
    private lateinit var etNumero: EditText
    private lateinit var btnEnviar: Button
    private var random = 0
    private val OUTRA = 1
    private var min = 1
    private var max = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvIntervalo = findViewById(R.id.tvIntervalo)
        this.etNumero = findViewById(R.id.etNumero)
        this.btnEnviar = findViewById(R.id.btnEnviar)

        this.btnEnviar.setOnClickListener(OnClickBotao())

        novoJogo()
    }

    fun novoJogo() {
        random = Random.nextInt(1, 101)
        min = 1
        max = 100
        tvIntervalo.text = "${min} e ${max}"

        Log.i("APP_ARROCHA", random.toString())
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
            var numero: Int

            if(this@MainActivity.etNumero.text.toString() != "") numero = this@MainActivity.etNumero.text.toString().toInt()
            else numero = 0

            val it = Intent(this@MainActivity, Resultado::class.java)

            if(numero == random || numero <= min || numero >= max) {
                it.putExtra("RESULTADO", "Perdeu")
                startActivityForResult(it, OUTRA)
            }
            else if(numero < random) min = numero
            else max = numero

            if(min + 1 == max - 1) {
                it.putExtra("RESULTADO", "Ganhou")
                startActivityForResult(it, OUTRA)
            }

            this@MainActivity.tvIntervalo.text = "${min} e ${max}"
            this@MainActivity.etNumero.text.clear()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        novoJogo()
    }
}
