package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnOk: Button
    private lateinit var etMensagem: EditText
    val OUTRA = 1
    private lateinit var btnSobre: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnOk = findViewById(R.id.btnMainOk)
        this.etMensagem =findViewById(R.id.etMainMensagem)
        this.btnSobre = findViewById(R.id.btnMainSobre)

        this.btnOk.setOnClickListener(OnClickBotao())

        this.btnSobre.setOnClickListener(OnClickBotaoSobre())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OUTRA) {
                val msg = data?.getStringExtra("MENSAGEM_VOLTA")
                this.etMensagem.setText(msg)
            }
        } else {
            Toast.makeText(this, "Voltou pelo Dispositivo", Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, OutraActivity::class.java)
            val msg = this@MainActivity.etMensagem.text.toString()

            it.putExtra("MENSAGEM", msg)

            //startActivity(it)
            startActivityForResult(it, OUTRA)
        }
    }

    inner class OnClickBotaoSobre: View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, SobreActivity::class.java)
            startActivity(it)
        }
    }
}
