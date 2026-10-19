package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapp.mvv.ItemViewModel
import com.example.myapp.mvv.MainViewModel
import com.example.myapp.mvv.SensorViewModel
import com.example.myapp.ui.theme.MyAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val viewModel: MainViewModel by viewModels()
        // val itemList : ItemViewModel by viewModels ()
        val sensorViewModel: SensorViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) { SensorView(sensorViewModel) }

            }
        }
    }
}

//Practica Corrutinas y Flow
@Composable
fun SensorView(viewModel: SensorViewModel) {
    val estado by viewModel.uiState.collectAsStateWithLifecycle()
    val contador by viewModel.contador.collectAsStateWithLifecycle()
    val umbral by viewModel.umbral.collectAsStateWithLifecycle()
    val tempCaliente by viewModel.temperaturaCaliente.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }

    // Colectar eventos SharedFlow
    LaunchedEffect(Unit) {
        viewModel.alertas.collect { snackbar.showSnackbar(it) }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbar) }) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Temperatura actual: ${estado.actual} °C",
                style = MaterialTheme.typography.headlineSmall)
            Text("Humedad: ${estado.humedad} %")
            Text("Promedio (últimas 10): ${"%.1f".format(estado.promedio)} °C")
            Text("Lecturas: ${estado.lecturas.joinToString()}")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = viewModel::iniciar, enabled = !estado.monitoreando) {
                    Text("Iniciar")
                }
                Button(onClick = viewModel::detener, enabled = estado.monitoreando) {
                    Text("Detener")
                }
            }

            //Actividad 7
            Text("Umbral de alerta: ${"%.1f".format(umbral)} °C")
            Slider(
                value = umbral,
                onValueChange = { viewModel.cambiarUmbral(it) },
                valueRange = 25f..40f
            )

            //Actividad 3
            Text("StateFlow caliente: $tempCaliente °C")

            HorizontalDivider()
            Button(onClick = { viewModel.iniciarContador(10) }) {
                Text("Cuenta regresiva 10s")
            }
            contador?.let {
                Text("Faltan: $it s", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Composable
fun ContentView(viewModel: MainViewModel){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotonColor()
        Spacer(modifier = Modifier.width(10.dp))
        if(viewModel.cargando){
            CircularProgressIndicator()
        }else{
            Text(viewModel.resultState)
        }

        Button(onClick = {viewModel.fetchData()}) {
            Text("Llamar a API")
        }
    }
}

@Composable
fun BotonColor(){
    var color: Boolean by remember { mutableStateOf(false) }
    Button(onClick = {color=!color},
        colors = ButtonDefaults.buttonColors(
            containerColor = if(color) Color.Blue else Color.Red
        )) {
        Text("Cambiar Color")
    }
}

@Composable
fun ItemsView(viewModel: ItemViewModel){
    val itemList = viewModel.itemList
    val lista by viewModel.lista.collectAsState()
    Column() {
        if(viewModel.cargando){
            CircularProgressIndicator()
        }else{
            LazyColumn() {
                items(lista){item->
                    Text(text = item.name)
                }
            }
        }
    }
}