package com.example.minhascorespreferidas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvCores: ListView
    private lateinit var fabAdd: FloatingActionButton

    private lateinit var cores: ArrayList<Cor>

    private val ADD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lvCores = findViewById(R.id.lvMainCores)
        this.fabAdd = findViewById(R.id.fabMainAdd)

        this.cores = ArrayList<Cor>()

        this.fabAdd.setOnClickListener(OnClickBotao())

//        this.lvCores.adapter = ArrayAdapter<Cor>(this, android.R.layout.simple_list_item_1, this.cores)
        this.lvCores.adapter = AdapterCor(this.cores, this)
        this.lvCores.setOnItemLongClickListener(ClickLongo())
    }

    private fun atualizaCores() {
        (this.lvCores.adapter as AdapterCor).notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ADD) {
            val cor = data?.getParcelableExtra<Cor>("COR")

            this.cores.add(cor!!)
            this.atualizaCores()
        }
    }

    inner class OnClickBotao: View.OnClickListener {
        override fun onClick(v: View?) {
            val it = Intent(this@MainActivity, AddCorActivity::class.java)
            startActivityForResult(it, ADD)
        }
    }

    inner class ClickLongo: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            this@MainActivity.cores.removeAt(position)
            this@MainActivity.atualizaCores()
            return true
        }
    }
}