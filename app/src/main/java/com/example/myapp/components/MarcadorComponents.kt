package com.example.myapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.ui.theme.GrisTexto
import com.example.myapp.ui.theme.LedAmbar
import com.example.myapp.ui.theme.LedRojo
import com.example.myapp.ui.theme.Naranja
import com.example.myapp.ui.theme.Negro
import com.example.myapp.ui.theme.Tablero

@Composable
fun TarjetaEquipo(
    nombre: String,
    puntos: Int,
    faltas: Int,
    vaGanando: Boolean,
    onPuntos: (Int) -> Unit,
    onFalta: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Tablero, RoundedCornerShape(16.dp))
            .border(
                2.dp,
                if (vaGanando) Naranja else Color.DarkGray,   // borde naranja al que va ganando
                RoundedCornerShape(16.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = nombre.uppercase(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        SpaceH(8.dp)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Negro, RoundedCornerShape(10.dp))
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = puntos.toString(),
                color = LedAmbar,
                fontSize = 64.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        }
        SpaceH(10.dp)

        // +1  +2  +3
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            BotonPunto("+1", Modifier.weight(1f)) { onPuntos(1) }
            BotonPunto("+2", Modifier.weight(1f)) { onPuntos(2) }
            BotonPunto("+3", Modifier.weight(1f)) { onPuntos(3) }
        }
        SpaceH(6.dp)
        OutlinedButton(
            onClick = { onPuntos(-1) },
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Gray),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        ) {
            Text("-1", color = GrisTexto, fontWeight = FontWeight.Bold)
        }

        SpaceH(14.dp)

        // Faltas
        Text("FALTAS", color = GrisTexto, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            BotonChico("-") { onFalta(-1) }
            Text(
                text = faltas.toString(),
                color = if (faltas >= 5) LedRojo else Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            BotonChico("+") { onFalta(1) }
        }
        Text(
            text = if (faltas >= 5) "BONUS" else " ",
            color = LedRojo,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun BotonPunto(texto: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Naranja, contentColor = Color.White),
        modifier = modifier.height(48.dp)
    ) {
        Text(texto, fontSize = 18.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun BotonChico(texto: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Color.Gray),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.size(36.dp)
    ) {
        Text(texto, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SpaceH(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun SpaceW(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.width(size))
}
