package com.example.minhascorespreferidas

import com.example.minhascorespreferidas.R
import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView


class AdapterCor: BaseAdapter {
    private val cores: ArrayList<Cor>
    private val act: Activity

    constructor(cores: ArrayList<Cor>, act: Activity) {
        this.cores = cores
        this.act = act
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = act.layoutInflater.inflate(R.layout.lista_cor, parent, false)
        val cor: Cor = cores.get(position)

        val llCor = view.findViewById<LinearLayout>(R.id.llListaCor)
        val tvNome = view.findViewById<TextView>(R.id.tvListaCorNome)
        val tvCodigo = view.findViewById<TextView>(R.id.tvListaCorCodigo)

        llCor.setBackgroundColor(Color.parseColor(cor.codigo))
        tvNome.text = cor.nome
        tvCodigo.text = cor.codigo

        return view
    }

    override fun getItem(position: Int): Any {
        return this.cores.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return this.cores.size
    }
}