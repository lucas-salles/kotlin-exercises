package com.example.popup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnMensagem: Button
    private lateinit var btnInput: Button
    private lateinit var btnData: Button
    private lateinit var btnHora: Button
    private lateinit var btnFaixa: Button
    private lateinit var btnEscolha: Button
    private lateinit var btnUnico: Button
    private lateinit var btnVarios: Button
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnMensagem = findViewById(R.id.btnMensagem)
        this.btnInput = findViewById(R.id.btnInput)
        this.btnData = findViewById(R.id.btnData)
        this.btnHora = findViewById(R.id.btnHora)
        this.btnFaixa = findViewById(R.id.btnFaixa)
        this.btnEscolha = findViewById(R.id.btnEscolha)
        this.btnUnico = findViewById(R.id.btnUnico)
        this.btnVarios = findViewById(R.id.btnVarios)

        this.btnMensagem.setOnClickListener({ mensagem() })
        this.btnInput.setOnClickListener({ input() })
        this.btnData.setOnClickListener({ data() })
        this.btnHora.setOnClickListener({ hora() })
        this.btnFaixa.setOnClickListener({ faixa() })
        this.btnEscolha.setOnClickListener({ escolha() })
        this.btnUnico.setOnClickListener({ unico() })
        this.btnVarios.setOnClickListener({ varios() })
    }

    fun mensagem() {
        val janela = AlertDialog.Builder(this)

        janela.setTitle("Mensagem")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Que bom!")

        janela.setPositiveButton("Ok") {dialog, which ->
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.setNeutralButton("Genérico") {dialog, which ->
            Toast.makeText(this, "Genérico", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun input() {
        val janela = AlertDialog.Builder(this)
        this.view = EditText(this)

        janela.setTitle("Input")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Digite uma frase")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") {dialog, which ->
            val msg = (this.view as EditText).text.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun data() {
        val janela = AlertDialog.Builder(this)
        this.view = DatePicker(this)

        janela.setTitle("DatePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Digite uma data")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") {dialog, which ->
            val dp = this.view as DatePicker
            val msg = "${dp.dayOfMonth}/${dp.month}/${dp.year}"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun hora() {
        val janela = AlertDialog.Builder(this)
        this.view = TimePicker(this)

        (this.view as TimePicker).setIs24HourView(true)

        janela.setTitle("TimePicker")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Digite uma hora")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") {dialog, which ->
            val tp = this.view as TimePicker
            var msg = ""

            if (Build.VERSION.SDK_INT < 23) {
                msg = "${tp.currentHour}:${tp.currentMinute}"
            } else {
                msg = "${tp.hour}:${tp.minute}"
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun faixa() {
        val janela = AlertDialog.Builder(this)
        this.view = SeekBar(this)

        val sb = this.view as SeekBar

        sb.max = 100

        janela.setTitle("SeekBar")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Defina um valor")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") {dialog, which ->
            val msg = (this.view as SeekBar).progress.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun escolha() {
        val janela = AlertDialog.Builder(this)
        this.view = Switch(this)

        janela.setTitle("Switch")
        janela.setIcon(R.mipmap.ic_launcher)
        janela.setMessage("Escolha um estado")
        janela.setView(this.view)

        janela.setPositiveButton("Ok") {dialog, which ->
            var msg = (this.view as Switch).isChecked.toString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun unico() {
        val janela = AlertDialog.Builder(this)
        var msg = "Opção 1"
        val opcoesArray = arrayOf("Opção 1", "Opção 2", "Opção 3", "Opção 4")


        janela.setTitle("Único")
        janela.setIcon(R.mipmap.ic_launcher)

        janela.setSingleChoiceItems(opcoesArray, 0) { dialogInterface, which ->
            msg = opcoesArray[which]
        }

        janela.setPositiveButton("Ok") {dialog, which ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }

    fun varios() {
        val janela = AlertDialog.Builder(this)
        var msg = ""
        val opcoesArray = arrayOf("Opção 1", "Opção 2", "Opção 3", "Opção 4")
        val checkedOpcoesArray =  booleanArrayOf(true, false, false, false)
        val opcoesList =  Arrays.asList(*opcoesArray)


        janela.setTitle("Vários")
        janela.setIcon(R.mipmap.ic_launcher)

        janela.setMultiChoiceItems(opcoesArray, checkedOpcoesArray) { dialog, which, isChecked ->
            checkedOpcoesArray[which] = isChecked
        }

        janela.setPositiveButton("Ok") {dialog, which ->
            for (i in checkedOpcoesArray.indices) {
                val checked = checkedOpcoesArray[i]
                if (checked) {
                    msg += opcoesList[i] + "\n"
                }
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        janela.setNegativeButton("Cancelar") {dialog, which ->
            Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
        }

        janela.create().show()
    }
}
