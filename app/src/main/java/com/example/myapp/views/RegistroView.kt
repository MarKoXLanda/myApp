package com.example.myapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.components.Alert
import com.example.myapp.components.Boton
import com.example.myapp.components.Caja
import com.example.myapp.components.Encabezado
import com.example.myapp.components.Etiqueta
import com.example.myapp.components.SpaceH
import com.example.myapp.data.StoreUser
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Pantalla de la IZQUIERDA del pizarrón
@Composable
fun RegistroView(navController: NavController, store: StoreUser) {
    val scope = rememberCoroutineScope()

    var nombre by rememberSaveable { mutableStateOf("") }
    var usuario by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var cargado by rememberSaveable { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    // Rellena usuario y pass con lo que se guardó en el Login (solo la primera vez)
    LaunchedEffect(Unit) {
        if (!cargado) {
            val user = store.getUser.first()
            nombre = user.nombre
            usuario = user.usuario
            pass = user.pass
            correo = user.correo
            telefono = user.telefono
            cargado = true
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        item {
            SpaceH(40.dp)
            Encabezado("Registro")
        }

        item {
            SpaceH(30.dp)
            Text(
                text = "Completa tus datos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            SpaceH(25.dp)
        }

        // Como en el dibujo: etiqueta a la izquierda y la caja a la derecha
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Renglon("Nombre:") {
                    Caja(nombre, { nombre = it })
                }
                Renglon("Usuario:") {
                    Caja(usuario, { usuario = it })
                }
                Renglon("Pass:") {
                    Caja(pass, { pass = it }, isPassword = true)
                }
                Renglon("Correo:") {
                    Caja(correo, { correo = it }, keyboardType = KeyboardType.Email)
                }
                Renglon("Tel:") {
                    Caja(telefono, { telefono = it }, keyboardType = KeyboardType.Phone)
                }
            }
        }

        item {
            SpaceH(35.dp)
            Boton("Guardar") {
                val campos = listOf(nombre, usuario, pass, correo, telefono)
                if (campos.any { it.isBlank() }) {
                    showAlert = true
                } else {
                    scope.launch {
                        store.saveDatos(nombre.trim(), usuario.trim(), pass, correo.trim(), telefono.trim())
                        // Va al perfil y borra Login/Registro del historial
                        navController.navigate("Perfil") {
                            popUpTo(navController.graph.id) { inclusive = true }
                        }
                    }
                }
            }
            SpaceH(40.dp)
        }
    }

    if (showAlert) {
        Alert(
            title = "Faltan datos",
            message = "Llena todos los campos para continuar",
            confirmText = "Aceptar",
            onConfirmClick = { showAlert = false },
            onDismissClick = { showAlert = false }
        )
    }
}

@Composable
fun Renglon(etiqueta: String, caja: @Composable () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 15.dp)
    ) {
        Etiqueta(etiqueta, modifier = Modifier.width(95.dp))
        caja()
    }
}
