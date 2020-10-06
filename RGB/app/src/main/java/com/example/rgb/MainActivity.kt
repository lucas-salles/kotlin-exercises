package com.example.rgb

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
    private lateinit var receiver: TelaReceiver
    private lateinit var filter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvVermelho = findViewById(R.id.tvVermelho)
        this.tvVerde = findViewById(R.id.tvVerde)
        this.tvAzul = findViewById(R.id.tvAzul)

        if (intent.hasExtra(Intent.EXTRA_TEXT)){
            this.tvVermelho.text = intent.getStringExtra(Intent.EXTRA_TEXT)
            this.tvVerde.text = intent.getStringExtra(Intent.EXTRA_TEXT)
            this.tvAzul.text = intent.getStringExtra(Intent.EXTRA_TEXT)
        }

        this.criarReceiver()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(this.receiver, this.filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(this.receiver)
    }

    private fun criarReceiver(){
        this.receiver = TelaReceiver()
        this.filter = IntentFilter()
        this.filter.addAction(Intent.ACTION_USER_PRESENT)
    }

    fun resetaTexto() {
        this.tvVermelho.text = "Red"
        this.tvVerde.text = "Green"
        this.tvAzul.text = "Blue"
    }

    inner class TelaReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            this@MainActivity.resetaTexto()
        }
    }
}