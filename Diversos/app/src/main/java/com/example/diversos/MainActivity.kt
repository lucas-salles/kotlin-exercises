package com.example.diversos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnHtml: Button
    private lateinit var btnDiscar: Button
    private lateinit var btnLigar: Button
    private lateinit var btnCompartilhar: Button
    private lateinit var btnEmail: Button
    private lateinit var btnPonto: Button
    private lateinit var btnRota: Button
    private lateinit var btnSms: Button
    private lateinit var btnYoutube: Button
    private lateinit var btnFoto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnHtml = findViewById(R.id.btnHtml)
        this.btnDiscar = findViewById(R.id.btnDiscar)
        this.btnLigar = findViewById(R.id.btnLigar)
        this.btnCompartilhar = findViewById(R.id.btnCompartilhar)
        this.btnEmail = findViewById(R.id.btnEmail)
        this.btnPonto = findViewById(R.id.btnPonto)
        this.btnRota = findViewById(R.id.btnRota)
        this.btnSms = findViewById(R.id.btnSms)
        this.btnYoutube = findViewById(R.id.btnYoutube)
        this.btnFoto = findViewById(R.id.btnFoto)

        this.btnHtml.setOnClickListener({ html() })
        this.btnDiscar.setOnClickListener({ discar() })
        this.btnLigar.setOnClickListener({ ligar() })
        this.btnCompartilhar.setOnClickListener({ compartilhar() })
        this.btnEmail.setOnClickListener({ email() })
        this.btnPonto.setOnClickListener({ ponto() })
        this.btnRota.setOnClickListener({ rota() })
        this.btnSms.setOnClickListener({ sms() })
        this.btnYoutube.setOnClickListener({ youtube() })
        this.btnFoto.setOnClickListener({ foto() })
    }

    fun html() {
        val uri = Uri.parse("http://www.ifpb.edu.br")

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun discar() {
        val uri = Uri.parse("tel:36121392")

        val intent = Intent(Intent.ACTION_DIAL, uri)

        startActivity(intent)
    }

    fun ligar() {
        val uri = Uri.parse("tel:36121392")

        val intent = Intent(Intent.ACTION_CALL, uri)

        val call = Manifest.permission.CALL_PHONE

        val granted = PackageManager.PERMISSION_GRANTED

        if (ContextCompat.checkSelfPermission(this, call) == granted) {
            startActivity(intent)
        }
    }

    fun compartilhar() {
        val intent = Intent(Intent.ACTION_SEND)

        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
        //intent.setPackage("com.whatsapp")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Compartilhar"))
        }
    }

    fun email() {
        val uri = Uri.parse("mailto:lucas.sales@academico.ifpb.edu.br")

        val intent = Intent(Intent.ACTION_SENDTO, uri)

        intent.putExtra(Intent.EXTRA_SUBJECT, "Assunto")
        intent.putExtra(Intent.EXTRA_TEXT, "Texto")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun ponto() {
        val uri = Uri.parse("geo:-7.1356496,-34.8760932")

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun rota() {
        val origem = "-7.1356496,-34.8760932"
        val destino = "-7.1181836,-34.8730402"
        val url = "http://maps.google.com/maps"

        val uri = Uri.parse("${url}?f=&saddr=${origem}+&daddr=${destino}")

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun sms() {
        val uri = Uri.parse("sms:36121392")

        val intent = Intent(Intent.ACTION_SENDTO, uri)

        intent.putExtra("sms_body", "Mensagem")

        startActivity(intent)
    }

    fun youtube() {
        val uri = Uri.parse("vnd.youtube://dglqGGyWbVo")

        val intent = Intent(Intent.ACTION_VIEW, uri)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun foto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivity(intent)
    }
}
