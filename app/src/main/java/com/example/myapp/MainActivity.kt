package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import coil3.compose.AsyncImage


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
     //   horizontalAlignment = Alignment.Start,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Button(
            onClick = {},
            shape = CircleShape,
            modifier = Modifier.padding(start = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Icono Corazon",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sumar")

        }

        Spacer(modifier = Modifier.padding(30.dp))
        Button(
            onClick = {},
            shape = CircleShape,
            modifier = Modifier.padding(start = 26.dp).size(width = 150.dp, height = 50.dp).padding(5.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Image(
                painter = painterResource(R.drawable.tamac),
                contentDescription = "tama",
              //  tint = Color.White,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tama")

        }
        Spacer(modifier = Modifier.width(8.dp))
        AsyncImage(
            model="https://i.pinimg.com/736x/f8/01/71/f801716d5be112d2a9b48da3eb663c01.jpg",
            contentDescription = "Imagen",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop


        )







    }




}


