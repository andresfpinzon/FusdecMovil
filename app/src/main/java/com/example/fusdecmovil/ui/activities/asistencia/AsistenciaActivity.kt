package com.example.fusdecmovil.ui.activities.asistencia

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fusdecmovil.R
import models.instructor.asistencia.Asistencia
import servicios.instructor.asistencia.AsistenciaServicio
import java.util.*

class AsistenciaActivity : AppCompatActivity() {

    private lateinit var tituloEditText: EditText
    private lateinit var fechaEditText: EditText
    private lateinit var usuarioIdEditText: EditText
    private lateinit var estudiantesEditText: EditText
    private lateinit var confirmarButton: Button
    private lateinit var cancelarButton: Button
    private val asistencias = mutableListOf<Asistencia>() // Lista de asistencias simulada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)

        tituloEditText = findViewById(R.id.tituloEditText)
        fechaEditText = findViewById(R.id.fechaEditText)
        usuarioIdEditText = findViewById(R.id.usuarioIdEditText)
        estudiantesEditText = findViewById(R.id.estudiantesEditText)
        confirmarButton = findViewById(R.id.confirmarButton)
        cancelarButton = findViewById(R.id.cancelarButton)

        confirmarButton.setOnClickListener {
            crearAsistencia()
        }

        cancelarButton.setOnClickListener {
            finish() // Cierra la Activity sin guardar datos
        }
    }

    private fun crearAsistencia() {
        val titulo = tituloEditText.text.toString()
        val fecha = fechaEditText.text.toString()
        val usuarioId = usuarioIdEditText.text.toString()
        val estudiantes = estudiantesEditText.text.toString().split(",")

        if (titulo.isBlank() || estudiantes.isEmpty()) {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevaAsistencia = AsistenciaServicio.crearAsistencia(
            asistencias,
            UUID.randomUUID().toString(),
            titulo,
            Date(),
            usuarioId,
            true,
            estudiantes
        )

        Toast.makeText(this, "Asistencia creada: ${nuevaAsistencia.tituloAsistencia}", Toast.LENGTH_SHORT).show()
    }
}
