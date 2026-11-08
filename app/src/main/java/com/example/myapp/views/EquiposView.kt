package com.example.myapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.OnBoardViews.ANIM_BOTE
import com.example.myapp.OnBoardViews.BalonAnimado
import com.example.myapp.components.SpaceH
import com.example.myapp.data.StoreMarcador
import com.example.myapp.ui.theme.GrisTexto
import com.example.myapp.ui.theme.Naranja
import com.example.myapp.ui.theme.Negro
import kotlinx.coroutines.launch

@Composable
fun EquiposView(navController: NavController, store: StoreMarcador) {
    val scope = rememberCoroutineScope()
    var local by rememberSaveable { mutableStateOf("") }
    var visita by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Negro)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp)
    ) {
        BalonAnimado(tipo = ANIM_BOTE, modifier = Modifier.size(140.dp))
        SpaceH(20.dp)
        Text("NUEVO PARTIDO", color = Naranja, fontSize = 28.sp, fontWeight = FontWeight.Black)
        Text("Escribe los nombres de los equipos", color = GrisTexto, fontSize = 14.sp)
        SpaceH(30.dp)

        CampoEquipo(local, { local = it }, "Equipo local")
        SpaceH(8.dp)
        Text("VS", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
        SpaceH(8.dp)
        CampoEquipo(visita, { visita = it }, "Equipo visitante")
        SpaceH(40.dp)

        Button(
            onClick = {
                scope.launch {
                    // Si los dejan vacíos se usan nombres por defecto
                    store.nuevoPartido(
                        local.trim().ifEmpty { "Local" },
                        visita.trim().ifEmpty { "Visita" }
                    )
                    navController.navigate("Marcador") {
                        popUpTo("Equipos") { inclusive = true }
                    }
                }
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Naranja, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "INICIAR PARTIDO",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Black
            )
        }
        SpaceH(20.dp)
    }
}

@Composable
fun CampoEquipo(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= 15) onValueChange(it) },   // nombres cortos para que quepan
        label = { Text(label) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Naranja,
            unfocusedBorderColor = Color.DarkGray,
            focusedLabelColor = Naranja,
            unfocusedLabelColor = GrisTexto,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Naranja
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
