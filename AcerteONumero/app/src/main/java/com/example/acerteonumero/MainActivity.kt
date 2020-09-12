package com.example.acerteonumero

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var etNumero: EditText
    private lateinit var btnOk: Button
    private lateinit var btnSobre: Button
    private lateinit var tvDica: TextView
    private lateinit var llAcertou: LinearLayout
    private lateinit var llErrou: LinearLayout
    private lateinit var llSobre: LinearLayout
    private lateinit var tvResposta: TextView
    private var random = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etNumero = findViewById(R.id.etNumero)
        this.btnOk = findViewById(R.id.btnOk)
        this.btnSobre = findViewById(R.id.btnSobre)
        this.tvDica = findViewById(R.id.tvDica)
        this.llAcertou = findViewById(R.id.llAcertou)
        this.llErrou = findViewById(R.id.llErrou)
        this.llSobre = findViewById(R.id.llSobre)
        this.tvResposta = findViewById(R.id.tvResposta)

        this.btnOk.setOnClickListener(OnClickBotao())

        this.btnSobre.setOnClickListener({
            this.llSobre.visibility = View.VISIBLE
        })

        this.llSobre.setOnClickListener({
            this.llSobre.visibility = View.INVISIBLE
        })

        this.llAcertou.setOnClickListener({
            this.llAcertou.visibility = View.INVISIBLE
            gerarDica()
        })

        this.llErrou.setOnClickListener({
            this.llErrou.visibility = View.INVISIBLE
            gerarDica()
        })

        gerarDica()
    }

    override fun onRestart() {
        super.onRestart()
        gerarDica()
    }

    fun gerarDica() {
        var dica = ""
        val divisores = ArrayList<Int>()

        random = Random().nextInt(100) + 1

        Log.i("APP_ACERTE", random.toString())

        this.etNumero.text.clear()

        for (i in 10 downTo 1) {
            if (random % i == 0) {
                divisores.add(i)
            }
        }

        dica += "Divisores: " + divisores.joinToString(" ") + "."

        if(random % 2 == 0) dica += "\nÉ par."
        else dica += "\nÉ impar."

        dica += "\nQuantidade de divisores: " + divisores.size + "."

        this.tvDica.text = dica
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
            val numero = this@MainActivity.etNumero.text.toString().toInt()

            if(numero == random) this@MainActivity.llAcertou.visibility = View.VISIBLE
            else {
                this@MainActivity.llErrou.visibility = View.VISIBLE
                this@MainActivity.tvResposta.text = "Resposta: " + random.toString()
            }
        }
    }
}
