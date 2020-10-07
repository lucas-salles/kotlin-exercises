package com.example.minhastarefas

import android.content.ContentValues
import android.content.Context

class TarefaDAO {
    var db: BancoHelper

    constructor(context: Context) {
        this.db = BancoHelper(context)
    }

    fun create(tarefa: Tarefa) {
        val cv = ContentValues()
        cv.put("descricao", tarefa.descricao)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)
        this.db.writableDatabase.insert("tarefa", null, cv)
    }

    fun read(id: Int): Tarefa? {
        val lista = ArrayList<Tarefa>()
        val colunas = arrayOf("id", "descricao", "prioridade", "status")
        val c = this.db.readableDatabase.query("tarefa", colunas, null, null, null, null, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val descricao = c.getString(c.getColumnIndex("descricao"))
                val prioridade = c.getInt(c.getColumnIndex("prioridade"))
                val status = c.getString(c.getColumnIndex("status"))
                lista.add(Tarefa(id, descricao, prioridade, status))
            } while (c.moveToNext())
        }

        for (tarefa in lista) {
            if (tarefa.id == id) return tarefa
        }

        return null
    }

    fun readAll(): ArrayList<Tarefa> {
        val lista = ArrayList<Tarefa>()
        val colunas = arrayOf("id", "descricao", "prioridade", "status")
        val c = this.db.readableDatabase.query("tarefa", colunas, null, null, null, null, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val descricao = c.getString(c.getColumnIndex("descricao"))
                val prioridade = c.getInt(c.getColumnIndex("prioridade"))
                val status = c.getString(c.getColumnIndex("status"))
                lista.add(Tarefa(id, descricao, prioridade, status))
            } while (c.moveToNext())
        }
        return lista
    }

    fun update(tarefa: Tarefa) {
        val cv = ContentValues()
        val where = "id = ?"
        val wherep = arrayOf(tarefa.id.toString())
        cv.put("descricao", tarefa.descricao)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)
        this.db.writableDatabase.update("tarefa", cv, where, wherep)
    }

    fun delete(id: Int) {
        val where = "id = ?"
        val wherep = arrayOf(id.toString())
        this.db.writableDatabase.delete("tarefa", where, wherep)
    }

    fun count(): Int {
        val colunas = arrayOf("id")
        val c = this.db.readableDatabase.query("tarefa", colunas, null, null, null, null, null)
        return c.count
    }
}