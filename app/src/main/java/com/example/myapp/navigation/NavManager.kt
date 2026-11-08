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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.OnBoardViews.MainOnBoarding
import com.example.myapp.data.StoreBoarding
import com.example.myapp.data.StoreMarcador
import com.example.myapp.ui.theme.Naranja
import com.example.myapp.ui.theme.Negro
import com.example.myapp.views.EquiposView
import com.example.myapp.views.MarcadorView
import kotlinx.coroutines.flow.first

@Composable
fun NavManager() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = remember { StoreBoarding(context) }
    val marcador = remember { StoreMarcador(context) }


    var inicio by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val terminado = dataStore.getStoreBoarding.first()
        val partido = marcador.getPartido.first()
        inicio = when {
            !terminado -> "OnBoarding"
            partido.local.isEmpty() -> "Equipos"
            else -> "Marcador"
        }
    }

    if (inicio == null) {
        Box(
            modifier = Modifier.fillMaxSize().background(Negro),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Naranja)
        }
    } else {
        NavHost(navController = navController, startDestination = inicio!!) {
            composable("OnBoarding") {
                MainOnBoarding(navController, dataStore)
            }
            composable("Equipos") {
                EquiposView(navController, marcador)
            }
            composable("Marcador") {
                MarcadorView(navController, marcador)
            }
        }
    }
}
