package com.example.myapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.model.Tarea


@Composable
fun TareaCard(tarea: Tarea, onCambiarEstado: (Boolean) -> Unit) {

    Card(
        shape = RectangleShape,
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarea.nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,


                )
                Text(
                    text = "Categoría: ${tarea.categoria}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = if (tarea.prioridadAlta) "Prioridad: Alta" else "Prioridad: Normal",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = if (tarea.terminada) "Estado: Terminada" else "Estado: Pendiente",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

            }

            Checkbox(
                checked = tarea.terminada,
                onCheckedChange = onCambiarEstado,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.DarkGray,
                    uncheckedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )

        }
    }
}
