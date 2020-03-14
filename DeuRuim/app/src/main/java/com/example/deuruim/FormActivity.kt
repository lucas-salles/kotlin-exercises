package com.example.deuruim

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.view.drawToBitmap

class FormActivity : AppCompatActivity() {
    private lateinit var etDescricao: EditText
    private lateinit var sbNota: SeekBar
    private lateinit var btnSalvar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnFoto: Button
    private lateinit var ivFoto: ImageView

    var FOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.etDescricao = findViewById(R.id.etFormDescricao)
        this.sbNota = findViewById(R.id.sbFormNota)

        this.btnCancelar = findViewById(R.id.btnFormCancelar)
        this.btnSalvar = findViewById(R.id.btnFormSalvar)

        this.btnFoto = findViewById(R.id.btnFormFoto)
        this.ivFoto = findViewById(R.id.ivFormFoto)

        this.btnSalvar.setOnClickListener({ salvar() })
        this.btnCancelar.setOnClickListener({
            finish()
        })

        this.btnFoto.setOnClickListener({ foto() })
    }

    fun salvar() {
        val descricao = this.etDescricao.text.toString()
        val nota = this.sbNota.progress
        val foto = this.ivFoto.drawToBitmap()

        val evento = Evento(descricao, nota, foto)

        val intent = Intent()
        intent.putExtra("EVENTO", evento)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }

    fun foto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == FOTO) {
            val imagem = data?.extras?.get("data") as Bitmap
            this.ivFoto.setImageBitmap(imagem)
        }
    }
}
