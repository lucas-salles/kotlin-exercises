package com.example.acerteonumero

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var etNumero: EditText
    private lateinit var btnOk: Button
    private lateinit var tvDica1: TextView
    private lateinit var tvDica2: TextView
    private lateinit var tvDica3: TextView
    private var random = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etNumero = findViewById(R.id.etNumero)
        this.btnOk = findViewById(R.id.btnOk)
        this.tvDica1 = findViewById(R.id.tvDica1)
        this.tvDica2 = findViewById(R.id.tvDica2)
        this.tvDica3 = findViewById(R.id.tvDica3)

        this.btnOk.setOnClickListener(OnClickBotao())
    }

    override fun onResume() {
        super.onResume()

        novoJogo()
    }

    fun novoJogo() {
        random = Random.nextInt(1, 101)

        Log.i("APP_ACERTE", random.toString())

        tvDica1.text = gerarDica1(random).joinToString(" ")
        tvDica2.text = gerarDica2(random)
        tvDica3.text = gerarDica3(random)
    }

    fun gerarDica1(numero: Int): ArrayList<Int> {
        val divisores = ArrayList<Int>()

        for (i in 10 downTo 1) {
            if (numero % i == 0) divisores.add(i)
        }

        return divisores
    }

    fun gerarDica2(numero: Int): String {
        var dica: String

        if(numero % 2 == 0) dica = "É par."
        else dica = "É impar."

        return dica
    }

    fun gerarDica3(numero: Int): String {
        val divisores = gerarDica1(numero)

        return (divisores.size + 1).toString()
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
            val numero = this@MainActivity.etNumero.text.toString().toInt()

            if(numero == random) Toast.makeText(this@MainActivity, "Acertou", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this@MainActivity, "Errou, valor gerado ${random}", Toast.LENGTH_SHORT).show()

            this@MainActivity.etNumero.text.clear()
            novoJogo()
        }
    }
}
