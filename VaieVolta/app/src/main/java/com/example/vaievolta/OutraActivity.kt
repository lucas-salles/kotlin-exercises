package com.example.vaievolta

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class OutraActivity : AppCompatActivity() {
    private lateinit var btnOk: Button
    private lateinit var etMensagem: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outra)

        this.btnOk = findViewById(R.id.btnOutraOk)
        this.etMensagem =findViewById(R.id.etOutraMensagem)

        this.btnOk.setOnClickListener(OnClickBotao())

        this.etMensagem.setText(intent.getStringExtra("MENSAGEM"))
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent()
            val msg = this@OutraActivity.etMensagem.text.toString()

            it.putExtra("MENSAGEM_VOLTA", msg)
            setResult(Activity.RESULT_OK, it)

            finish()
        }
    }
}
