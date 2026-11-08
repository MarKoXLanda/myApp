package com.example.myapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.components.SpaceH
import com.example.myapp.components.TarjetaEquipo
import com.example.myapp.data.StoreMarcador
import com.example.myapp.model.Partido
import com.example.myapp.ui.theme.GrisTexto
import com.example.myapp.ui.theme.LedRojo
import com.example.myapp.ui.theme.Naranja
import com.example.myapp.ui.theme.Negro
import com.example.myapp.ui.theme.Tablero
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MarcadorView(navController: NavController, store: StoreMarcador) {
    val scope = rememberCoroutineScope()

    // Todo lo que se ve sale del DataStore. Cada botón escribe ahí y la pantalla se actualiza sola
    val partido by store.getPartido.collectAsState(initial = Partido())

    var corriendo by rememberSaveable { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    // ----- Reloj -----
    // Mientras "corriendo" sea true, cada segundo le resta 1 al tiempo guardado
    LaunchedEffect(corriendo) {
        while (corriendo) {
            delay(1000)
            val quedan = store.restarSegundo()
            if (quedan == 0) corriendo = false    // se acabó el cuarto
        }
    }

    val minutos = partido.tiempo / 60
    val segundos = partido.tiempo % 60
    val reloj = "%02d:%02d".format(minutos, segundos)
    val textoCuarto = if (partido.cuarto > 4) "TIEMPO EXTRA" else "CUARTO ${partido.cuarto}"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Negro)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            "BASKET SCORE",
            color = Naranja,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 4.sp
        )
        SpaceH(12.dp)

        // ----- Tablero del reloj -----
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Tablero, RoundedCornerShape(16.dp))
                .border(2.dp, Naranja, RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            // < CUARTO 1 >
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = {
                        corriendo = false
                        scope.launch { store.cambiarCuarto(partido.cuarto - 1) }
                    },
                    enabled = partido.cuarto > 1
                ) { Text("◀", color = if (partido.cuarto > 1) Color.White else Color.DarkGray, fontSize = 20.sp) }

                Text(
                    textoCuarto,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                TextButton(
                    onClick = {
                        corriendo = false
                        scope.launch { store.cambiarCuarto(partido.cuarto + 1) }
                    },
                    enabled = partido.cuarto < StoreMarcador.ULTIMO_CUARTO
                ) {
                    Text(
                        "▶",
                        color = if (partido.cuarto < StoreMarcador.ULTIMO_CUARTO) Color.White else Color.DarkGray,
                        fontSize = 20.sp
                    )
                }
            }

            // 10:00
            Text(
                text = reloj,
                color = LedRojo,
                fontSize = 68.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (partido.tiempo == 0) "FIN DEL CUARTO" else " ",
                color = Naranja,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black
            )
            SpaceH(6.dp)

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = { corriendo = !corriendo },
                    enabled = partido.tiempo > 0,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (corriendo) Color.DarkGray else Naranja,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (corriendo) "PAUSAR" else "INICIAR", fontWeight = FontWeight.Black)
                }
                OutlinedButton(
                    onClick = {
                        corriendo = false
                        scope.launch { store.reiniciarReloj() }
                    },
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("REINICIAR", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }

        SpaceH(14.dp)

        // ----- Equipos -----
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            TarjetaEquipo(
                nombre = partido.local,
                puntos = partido.puntosLocal,
                faltas = partido.faltasLocal,
                vaGanando = partido.puntosLocal > partido.puntosVisita,
                onPuntos = { n -> scope.launch { store.sumarPuntos(true, n) } },
                onFalta = { n -> scope.launch { store.sumarFalta(true, n) } },
                modifier = Modifier.weight(1f)
            )
            TarjetaEquipo(
                nombre = partido.visita,
                puntos = partido.puntosVisita,
                faltas = partido.faltasVisita,
                vaGanando = partido.puntosVisita > partido.puntosLocal,
                onPuntos = { n -> scope.launch { store.sumarPuntos(false, n) } },
                onFalta = { n -> scope.launch { store.sumarFalta(false, n) } },
                modifier = Modifier.weight(1f)
            )
        }

        SpaceH(20.dp)
        TextButton(onClick = { showAlert = true }) {
            Text("NUEVO PARTIDO", color = GrisTexto, fontWeight = FontWeight.Bold)
        }
        SpaceH(10.dp)
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            containerColor = Tablero,
            title = { Text("¿Nuevo partido?", color = Color.White, fontWeight = FontWeight.Bold) },
            text = { Text("Se borrará el marcador actual.", color = GrisTexto) },
            confirmButton = {
                Button(
                    onClick = {
                        showAlert = false
                        corriendo = false
                        scope.launch {
                            store.borrarPartido()
                            navController.navigate("Equipos") {
                                popUpTo("Marcador") { inclusive = true }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Naranja)
                ) { Text("Borrar", color = Color.White) }
            },
            dismissButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("Cancelar", color = Color.White)
                }
            }
        )
    }
}
