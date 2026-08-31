package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {

                Formulario()
                }
            }
        }
    }

@Composable
fun Formulario2(){
    val listaBotones = listOf(
        Estructura("Rojo", Color.Red),
        Estructura("Verde", Color.Green),
        Estructura("Amarillo", Color.Yellow),
        Estructura("Azul", Color.Blue),
        Estructura("Cyan", Color.Cyan),
        Estructura("Dark Grey", Color.DarkGray)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text("Jetpack")
    Spacer(modifier = Modifier.height(10.dp))
    Text("Compose")
    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ){items(listaBotones){estructura->
        Botones(estructura)
    }

    }
}
@Composable
fun Formulario(){



    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
    ){
        Text("Hola", fontSize = 48.sp, fontWeight = FontWeight.Bold, color= Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Black).fillMaxWidth())
        Spacer(modifier = Modifier.padding(10.dp))
        Texto("Jetpack", Color.Red)
        Espacio(10)
        Texto("Compose", Color.Yellow)
    }
}

@Composable
fun Texto(texto:String, color: Color){
    Text(
        text=texto,fontSize = 48.sp, fontWeight = FontWeight.Bold, color= color,
        textAlign = TextAlign.Center,
        modifier = Modifier.background(Color.Black).fillMaxWidth()
    )
}

@Composable
fun Espacio(espacio: Int){
    Spacer(modifier=Modifier.padding(espacio.dp))

}

@Composable
fun Botones(estructura: Estructura){
    Button(
        onClick = {},
        modifier = Modifier.background(estructura.color)
    ){
        Text(text = estructura.nombre)
    }

}