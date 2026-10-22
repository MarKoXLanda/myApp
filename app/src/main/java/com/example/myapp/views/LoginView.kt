package com.example.myapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.components.Alert
import com.example.myapp.components.Boton
import com.example.myapp.components.Caja
import com.example.myapp.components.Encabezado
import com.example.myapp.components.Etiqueta
import com.example.myapp.components.SpaceH
import com.example.myapp.data.StoreUser
import kotlinx.coroutines.launch

// Pantalla del CENTRO del pizarrón
@Composable
fun LoginView(navController: NavController, store: StoreUser) {
    val scope = rememberCoroutineScope()
    var usuario by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item {
            SpaceH(40.dp)
            Encabezado("MyApp - Login")
        }

        // El cuadro grande del dibujo
        /*
        item {
            SpaceH(40.dp)
            Image(
                painter = painterResource(R.drawable.perfil),
                contentDescription = "Logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .border(3.dp, Color.Black, RectangleShape)
            )
        }
        */
        item {
            SpaceH(40.dp)
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Ícono de perfil",
                tint = Color.Gray,
                modifier = Modifier
                    .size(170.dp)
                    .border(3.dp, Color.Black, RectangleShape)
                    .padding(16.dp)
            )
        }

        item {
            SpaceH(50.dp)
            Column(modifier = Modifier.padding(horizontal = 40.dp)) {
                Etiqueta("Login")
                SpaceH(5.dp)
                Caja(
                    value = usuario,
                    onValueChange = { usuario = it },
                    modifier = Modifier.fillMaxWidth()
                )

                SpaceH(25.dp)
                Etiqueta("Pass:")
                SpaceH(5.dp)
                Caja(
                    value = pass,
                    onValueChange = { pass = it },
                    isPassword = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            SpaceH(35.dp)
            Boton("Entrar") {
                if (usuario.isBlank() || pass.isBlank()) {
                    showAlert = true
                } else {
                    scope.launch {
                        store.saveLogin(usuario.trim(), pass)
                        navController.navigate("Registro")
                    }
                }
            }
        }

        // El "Registro" subrayado del dibujo
        item {
            SpaceH(30.dp)
            Text(
                text = "Registro",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { navController.navigate("Registro") }
                    .padding(8.dp)
            )
            SpaceH(40.dp)
        }
    }

    if (showAlert) {
        Alert(
            title = "Campos vacíos",
            message = "Escribe tu usuario y contraseña",
            confirmText = "Aceptar",
            onConfirmClick = { showAlert = false },
            onDismissClick = { showAlert = false }
        )
    }
}
