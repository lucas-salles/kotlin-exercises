package com.example.testargb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var etTexto: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etTexto = findViewById(R.id.etTexto)
        this.btnEnviar = findViewById(R.id.btnEnviar)

        this.btnEnviar.setOnClickListener(OnClickBotao())
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent()
            it.putExtra("TEXTO", this@MainActivity.etTexto.text.toString())
            setResult(Activity.RESULT_OK, it)
            finish()
        }
    }
}