package com.example.myapp

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Contenido()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun Contenido() {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotonNormal()
        Espacio()
        BotonNormal2()
        Espacio()
        BotonTexto()
        Espacio()
        BotonOutLine()
        Espacio()
        BotonIcono()
        Espacio()
        BotonFlotante()
        Espacio()

    }

}

@Composable
fun BotonNormal(){
    Button(onClick = {}) {
        Text("Mi Boton", fontSize = 30.sp)
    }
}

@Composable
fun Espacio(){
    Spacer(modifier = Modifier.size(10.dp))
}

@Composable
fun BotonNormal2(){
    Button(onClick = {}, enabled = false) {
        Text("Mi boton2", fontSize = 30.sp)
    }
}

@Composable
fun BotonTexto(){
    TextButton(onClick = {}) {
        Text("Mi Boton", fontSize = 30.sp)
    }
}

@Composable
fun BotonOutLine(){
    OutlinedButton(onClick = {}, border = BorderStroke(3.dp,Color.Red)) {
        Text("Mi Boton", fontSize = 30.sp)
    }
}

@Composable
fun BotonIcono(){
    IconButton(onClick = {}) {
        Icon(

            painter = painterResource(R.drawable.reiniciar),
            contentDescription = null
        )
    }
}

@Composable
fun BotonFlotante(){
    FloatingActionButton(onClick = {}, contentColor = Color.Blue,
        containerColor = Color.Red) {

        Icon(
            painter = painterResource(R.drawable.reiniciar),
            contentDescription = null,
            modifier = Modifier.size(30.dp )
        )
    }
}

