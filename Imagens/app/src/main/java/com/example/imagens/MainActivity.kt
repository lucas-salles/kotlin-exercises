package com.example.imagens

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var btnCamera: Button
    private lateinit var btnArquivo: Button
    private lateinit var btnDownload: Button
    private lateinit var ivImagem: ImageView

    val CAMERA = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnCamera = findViewById(R.id.btnCamera)
        this.btnArquivo = findViewById(R.id.btnArquivo)
        this.btnDownload = findViewById(R.id.btnDownload)

        this.ivImagem = findViewById(R.id.ivImagem)

        this.btnCamera.setOnClickListener({ camera() })
        this.btnArquivo.setOnClickListener({ arquivo() })
        this.btnDownload.setOnClickListener({ download() })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA) {
            val imagem = data?.extras?.get("data") as Bitmap
            this.ivImagem.setImageBitmap(imagem)
        }
    }

    fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    fun arquivo() {
        this.ivImagem.setImageResource(R.drawable.super_mario)
    }

    fun downloadDaImagem(url: String) : Bitmap {
        URL(url).openStream().use {
            val imagem = BitmapFactory.decodeStream(it)
            return imagem
        }
    }

    fun download() {
        var selecionado: String? = null
        val resolucoes = resources.getStringArray(R.array.sm_resolution)

        val janela = AlertDialog.Builder(this)
        janela.setTitle("Escolha uma resolução")
        janela.setIcon(R.mipmap.ic_launcher)

        janela.setSingleChoiceItems(resolucoes, 0) { dialogInterface, which ->
            selecionado = resolucoes[which]
        }

        janela.setPositiveButton("Ok") { dialog, i ->
            if (selecionado == null) {
                imagemHandler("ldpi")
            } else {
                imagemHandler(selecionado)
            }
        }

        janela.create().show()
    }

    private fun imagemHandler(resolucao: String?) {
        val handler = Handler()
        val url = "http://www.valeria.eti.br/sm/sm_${resolucao}.png"
        Thread {
            val imagem = this.downloadDaImagem(url)

            handler.post{
                this.ivImagem.setImageBitmap(imagem)
            }
        }.start()
    }
}
