package com.example.fusdecmovil.ui.activities.Comando

import android.os.Bundle
import android.widget.Button
import android.widget.*
import android.widget.ListView
import models.administrativo.brigada.Brigada
import com.example.fusdecmovil.R
import models.administrativo.comando.ComandoServicio
import java.util.*

class ComandoActivity : appCompatActivity(){

    private lateinit var nombreComandoEditText: EditText
    private lateinit var ubicacionComandoEditText: EditText
    private lateinit var fundacionIdEditText: EditText
    private lateinit var brigadasIdEditText: EditText
    private lateinit var confirmarButton: Button
    private lateinit var cancelarButton: Button
    private lateinit var ComandoListView: ListView
    private val Comandos = mutableListOf<Brigada>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comando)


        nombreComandoEditText = finViewById(R.id.nombreComandoEditText)
        ubicacionComandoEditText = findViewById(R.id.ubicacionComandoEditText)
        fundacionIdEditText = findViewById(R.id.fundacionEditText)
        brigadasIdEditText = findViewById(R.id.brigadaEditText)
        confirmarButton = findViewById(R.id.confirmarButton)
        cancelarButton = findViewById(R.id.cancelarButton)
        ComandoListView = findViewById(R.id.ComandoListView)

        ConfirmarButton.setOnClickListener{
            crearComando()
        }

        cancelarButton.setOnClickListener {
            finish()
        }

        listarComandos()

        private fun crearComando() {
            val nombreComando = nombreComandoEditText.text.toString()
            val ubicacionComando = ubicacionComandoEditText.text.toString()
            val fundacionId = fundacionIdEditText.text.toString()
            val brigadas = brigadasIdEditText.text.toString().split(",")
        }

        if (nombreComando.isBlank() || brigadas.isEmpty()) {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoComando = ComandoServicio.crearComando(
            Comandos,
            UUID.randomUUID().toString(),
            nombreComando,
            ubicacionComando,
            fundacionId,
            brigadas
        )

        Toast.makeText(this, "comando creado: ${nuevoComando.nombreComando}", Toast.LENGTH_SHORT).show()
        listarComandos()
    }

    private fun listarComandos() {
        val comandosActivos = ComandoServicio.listarComandosActivos(Comandos)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, comandosActivos.map {it.nombreComando })
        ComandoListView.adapter = adapter
    }

}