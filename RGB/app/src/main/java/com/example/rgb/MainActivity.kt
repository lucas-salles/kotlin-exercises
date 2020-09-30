package com.example.rgb

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var tvVermelho: TextView
    private lateinit var tvVerde: TextView
    private lateinit var tvAzul: TextView
    val RGB = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvVermelho = findViewById(R.id.tvVermelho)
        this.tvVerde = findViewById(R.id.tvVerde)
        this.tvAzul = findViewById(R.id.tvAzul)

        val intent = Intent("RGB")
        startActivityForResult(intent, RGB)

        this.registerReceiver(this.broadcastReceiver, IntentFilter("TELA"));
    }

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            this@MainActivity.resetaTexto()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(this.broadcastReceiver);
    }

    fun resetaTexto() {
        this.tvVermelho.text = "Vermelho"
        this.tvVerde.text = "Verde"
        this.tvAzul.text = "Azul"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == RGB) {
            this@MainActivity.tvVermelho.text = data?.getStringExtra("TEXTO").toString()
            this@MainActivity.tvVerde.text = data?.getStringExtra("TEXTO").toString()
            this@MainActivity.tvAzul.text = data?.getStringExtra("TEXTO").toString()
        }
    }
}