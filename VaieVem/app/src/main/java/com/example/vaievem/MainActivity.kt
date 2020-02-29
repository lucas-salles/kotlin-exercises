package com.example.vaievem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var btnSucesso: Button
    private lateinit var btnErro: Button
    private lateinit var llSucesso: LinearLayout
    private lateinit var llErro: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnSucesso = findViewById(R.id.btnSucesso)
        this.btnSucesso.setOnClickListener({
            this.llSucesso.visibility = View.VISIBLE
        })

        this.btnErro = findViewById(R.id.btnErro)
        this.btnErro.setOnClickListener({
            this.llErro.visibility = View.VISIBLE
        })

        this.llSucesso = findViewById(R.id.llSucesso)
        this.llSucesso.setOnClickListener({
            this.llSucesso.visibility = View.INVISIBLE
        })

        this.llErro = findViewById(R.id.llErro)
        this.llErro.setOnClickListener({
            this.llErro.visibility = View.INVISIBLE
        })
    }
}
