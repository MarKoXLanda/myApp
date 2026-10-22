package com.example.myapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.data.StoreUser
import com.example.myapp.views.LoginView
import com.example.myapp.views.PerfilView
import com.example.myapp.views.RegistroView
import kotlinx.coroutines.flow.first

@Composable
fun Navigate() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val store = remember { StoreUser(context) }

    // Se decide UNA vez al abrir la app a qué pantalla mandar
    var inicio by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val user = store.getUser.first()
        inicio = when {
            user.completo -> "Perfil"             // ya tiene todo guardado
            user.usuario.isNotEmpty() -> "Registro" // hizo login pero no completó datos
            else -> "Login"
        }
    }

    if (inicio == null) {
        // Mientras se lee el DataStore
        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.DarkGray)
        }
    } else {
        NavHost(
            navController = navController, startDestination = inicio!!
        ) {
            composable("Login") {
                LoginView(navController, store)
            }
            composable("Registro") {
                RegistroView(navController, store)
            }
            composable("Perfil") {
                PerfilView(navController, store)
            }
        }
    }
}
