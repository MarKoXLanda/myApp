package com.example.myapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.components.Alert
import com.example.myapp.components.Boton
import com.example.myapp.components.SpaceH
import com.example.myapp.data.StoreUser
import com.example.myapp.model.Usuario
import kotlinx.coroutines.launch

// Pantalla de la DERECHA del pizarrón
@Composable
fun PerfilView(navController: NavController, store: StoreUser) {
    val scope = rememberCoroutineScope()
    val user by store.getUser.collectAsState(initial = Usuario())
    var showAlert by remember { mutableStateOf(false) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        // Arriba: usuario y nombre (el rectangulito del dibujo)
        item {
            SpaceH(40.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(Color.DarkGray)
                    .border(2.dp, Color.Black, RectangleShape)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.banner1),
                    contentDescription = "Foto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(
                        text = "@${user.usuario}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = user.nombre,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                }
            }
        }

        // El óvalo del dibujo
        item {
            SpaceH(35.dp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .background(Color.White, RoundedCornerShape(percent = 50))
                    .border(3.dp, Color.Black, RoundedCornerShape(percent = 50))
                    .padding(vertical = 70.dp, horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.images1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                )
                SpaceH(15.dp)
                Text(
                    text = "HOLA!!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                SpaceH(25.dp)
                Dato("Nombre", user.nombre)
                Dato("Usuario", user.usuario)
                Dato("Correo", user.correo)
                Dato("Teléfono", user.telefono)
            }
        }

        item {
            SpaceH(35.dp)
            Boton("Borrar datos", color = Color(0xFFB00020)) {
                //showAlert = true
                scope.launch {
                    store.clear()
                    navController.navigate("Login") {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            }
            SpaceH(40.dp)
        }
    }



/*
    if (showAlert) {
        Alert(
            title = "Borrar datos",
            message = "Se eliminar todo lo guardado y regresarás al inicio",
            confirmText = "Borrar",
            dismissText = "Cancelar",
            onConfirmClick = {
                showAlert = false
                scope.launch {
                    store.clear()
                    navController.navigate("Login") {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            },
            onDismissClick = { showAlert = false }
        )
    }

 */
}

@Composable
fun Dato(label: String, valor: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(text = "-$label-", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Text(text = valor, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}
