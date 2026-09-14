package com.example.myapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear // Usaremos Clear como alternativa para restar si Remove no está en la librería core
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.width


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Screen()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun Screen() {
    var contadorA by remember { mutableStateOf(0) }
    var contadorR by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
       // horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(60.dp).size(80.dp).background(
                color = Color.Cyan,
                shape = CircleShape
            )
        ) {
            Text(
                text="$contadorA", fontSize = 70.sp, color = Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {contadorA++},
            shape = CircleShape,
            modifier = Modifier.padding(start = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Icono Sumar",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sumar")

        }

        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {contadorA--},
            shape = CircleShape,
            modifier = Modifier.padding(start = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Icono Restar",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Restar")

        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(60.dp).size(80.dp).background(
                color = Color.Red,
                shape = CircleShape
            )
        ) {
            Text(
                text="$contadorR", fontSize = 70.sp, color = Color.Black
            )
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {contadorR++},
            shape = CircleShape,
            modifier = Modifier.padding(end = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Icono SumarR",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sumar")

        }

        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {contadorR--},
            shape = CircleShape,
            modifier = Modifier.padding(end = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Icono RestarR",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Restar")

        }

        Spacer(modifier = Modifier.padding(40.dp))
        Button(
            onClick = {
                contadorR=0
                contadorA=0},
            shape = CircleShape,
            modifier = Modifier.padding(end=120.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ){
            Text("Reiniciar")
        }

    }
}


