package com.example.minhascorespreferidas

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddCorActivity : AppCompatActivity() {
    private lateinit var etNome: EditText
    private lateinit var etCodigo: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cor)

        this.etNome = findViewById(R.id.etAddNome)
        this.etCodigo = findViewById(R.id.etAddCodigo)
        this.btnCadastrar = findViewById(R.id.btnAddCadastrar)

        this.btnCadastrar.setOnClickListener(OnClickBotao())
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val nome = this@AddCorActivity.etNome.text.toString()
            val codigo = this@AddCorActivity.etCodigo.text.toString()

            try {
                Color.parseColor(codigo)

                val cor = Cor(nome, codigo)

                val it = Intent()
                it.putExtra("COR", cor)

                setResult(Activity.RESULT_OK, it)

                finish()
            } catch (iae: IllegalArgumentException) {
                Toast.makeText(this@AddCorActivity, "Código hexadeciaml inválido. Tente novamente!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}