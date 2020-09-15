package com.example.arrocheonumero

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Resultado : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        this.tvResultado = findViewById(R.id.tvResultado)
        this.btnOk = findViewById(R.id.btnOk)

        val resultado = intent.getStringExtra("RESULTADO")

        if(resultado == "Ganhou") tvResultado.setTextColor(getResources().getColor(R.color.colorPrimary))
        else tvResultado.setTextColor(getResources().getColor(R.color.colorAccent))

        tvResultado.text = resultado

        this.btnOk.setOnClickListener({
            val it = Intent()
            setResult(Activity.RESULT_OK, it)
            finish()
        })
    }
}
