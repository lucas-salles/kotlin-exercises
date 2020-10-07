package com.example.minhastarefas

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class TarefaAdapter(var context: Context, var lista: ArrayList<Tarefa>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View

        if (convertView != null) view = convertView
        else {
            val inflate = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflate.inflate(R.layout.tarefa_layout, null)
        }

        val ivIcone = view.findViewById<ImageView>(R.id.ivTarefaIcone)
        val tvDescricao = view.findViewById<TextView>(R.id.tvTarefaNome)

        val tarefa = this.lista.get(position)

        if (tarefa.status == "Aberto") {
            ivIcone.setImageResource(R.drawable.ic_baseline_schedule_24)
            ivIcone.setColorFilter(Color.parseColor("#FF0000"))
        } else if (tarefa.status == "Executando") {
            ivIcone.setImageResource(R.drawable.ic_baseline_build_24)
            ivIcone.setColorFilter(Color.parseColor("#0000FF"))
        } else {
            ivIcone.setImageResource(R.drawable.ic_baseline_check_circle_24)
            ivIcone.setColorFilter(Color.parseColor("#00FF00"))
        }

        tvDescricao.text = tarefa.descricao

        return view
    }

    override fun getItem(position: Int): Any {
        return this.lista.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return this.lista.size
    }
}