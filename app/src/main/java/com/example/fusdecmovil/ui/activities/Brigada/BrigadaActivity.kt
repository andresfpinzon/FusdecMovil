package com.example.fusdecmovil.ui.activities.Brigada

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fusdecmovil.R
import models.administrativo.brigada.Brigada
import models.administrativo.Brigada.BrigadaServicio
import java.util.*

class BrigadaActivity : AppCompatActivity() {

    private lateinit var nombreBrigadaEditText: EditText
    private lateinit var ubicacionBrigadaEditText: EditText
    private lateinit var comandoIdEditText: EditText
    private lateinit var unidadesEditText: EditText
    private lateinit var confirmarButton: Button
    private lateinit var cancelarButton: Button
    private lateinit var brigadasListView: ListView
    private val brigadas = mutableListOf<Brigada>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brigada)

        nombreBrigadaEditText = findViewById(R.id.nombreBrigadaEditText)
        ubicacionBrigadaEditText = findViewById(R.id.ubicacionBrigadaEditText)
        comandoIdEditText = findViewById(R.id.comandoIdEditText)
        unidadesEditText = findViewById(R.id.unidadesEditText)
        confirmarButton = findViewById(R.id.confirmarButton)
        cancelarButton = findViewById(R.id.cancelarButton)
        brigadasListView = findViewById(R.id.brigadasListView)

        confirmarButton.setOnClickListener {
            crearBrigada()
        }

        cancelarButton.setOnClickListener {
            finish()
        }

        listarBrigadas()
    }

    private fun crearBrigada() {
        val nombreBrigada = nombreBrigadaEditText.text.toString()
        val ubicacionBrigada = ubicacionBrigadaEditText.text.toString()
        val comandoId = comandoIdEditText.text.toString()
        val unidades = unidadesEditText.text.toString().split(",")

        if (nombreBrigada.isBlank() || unidades.isEmpty()) {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevaBrigada = BrigadaServicio.crearBrigada(
            brigadas,
            UUID.randomUUID().toString(),
            nombreBrigada,
            ubicacionBrigada,
            comandoId,
            unidades
        )

        Toast.makeText(this, "Brigada creada: ${nuevaBrigada.nombreBrigada}", Toast.LENGTH_SHORT).show()
        listarBrigadas()
    }

    private fun listarBrigadas() {
        val brigadasActivas = BrigadaServicio.listarBrigadasActivas(brigadas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, brigadasActivas.map { it.nombreBrigada })
        brigadasListView.adapter = adapter
    }
}