package com.example.fusdecmovil

import android.content.Intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fusdecmovil.ui.activities.asistencia.AsistenciaActivity
import com.example.fusdecmovil.ui.theme.FusdecMovilTheme
import android.content.Context


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import com.example.fusdecmovil.ui.activities.Brigada.BrigadaActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FusdecMovilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
//                        onAsistenciaClick = { abrirAsistenciaActivity() }
                        onBrgadaclick = { abrirBrigadaActivity()}
                    )
                }
            }
        }
    }

    private fun abrirAsistenciaActivity() {
        val intent = Intent(this, AsistenciaActivity::class.java)
        startActivity(intent)
    }
    private fun abrirBrigadaActivity() {
        val intent = Intent(this, BrigadaActivity::class.java )
        startActivity(intent)
    }

}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onBrigadaActivity: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bienvenido a FusdecMovil!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBrigadaActivity) {
            Text("Ir a Brigada")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FusdecMovilTheme {
        MainScreen(onAsistenciaClick = {})
    }
}