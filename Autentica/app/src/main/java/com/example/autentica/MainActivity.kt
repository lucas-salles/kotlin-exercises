package com.example.autentica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etSenha: EditText
    private lateinit var btnCancelar: Button
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etUsuario = findViewById(R.id.etUsuario)
        this.etSenha = findViewById(R.id.etSenha)
        this.btnCancelar = findViewById(R.id.btnCancelar)
        this.btnOk = findViewById(R.id.btnOk)

        this.btnCancelar.setOnClickListener({
            Log.i("APP_AUTENTICA", "i - cancelou")
            Log.e("APP_AUTENTICA", "e - cancelou")
            Log.w("APP_AUTENTICA", "w - cancelou")
            this.etUsuario.text.clear()
            this.etSenha.text.clear()
            Toast.makeText(this, "Você cancelou!", Toast.LENGTH_SHORT).show()
        })

        this.btnOk.setOnClickListener(OnClickBotao())
    }

    inner class OnClickBotao : View.OnClickListener {
        override fun onClick(v: View?) {
            var usuario = this@MainActivity.etUsuario.text.toString()
            var senha = this@MainActivity.etSenha.text.toString()

            Log.i("APP_AUTENTICA", "$usuario - $senha")

            if (usuario == "admin" && senha == "1234") {
                Toast.makeText(this@MainActivity, "Sucesso!", Toast.LENGTH_SHORT).show()
                Log.i("APP_AUTENTICA", "Deu certo")

               val intent = Intent(this@MainActivity, Main2Activity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this@MainActivity, "Erro!", Toast.LENGTH_SHORT).show()
                Log.i("APP_AUTENTICA", "Deu errado")
            }
        }
    }
}
