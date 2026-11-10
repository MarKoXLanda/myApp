package com.example.myapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.components.Encabezado
import com.example.myapp.components.Etiqueta
import com.example.myapp.components.SpaceH
import com.example.myapp.viewModel.TareasViewModel


@Composable
fun NuevaTareaView(navController: NavController, viewModel: TareasViewModel) {

    var nombre by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf("Escuela") }
    var prioridadAlta by rememberSaveable { mutableStateOf(false) }
    var mostrarError by rememberSaveable { mutableStateOf(false) }

    val categorias = listOf("Escuela", "Trabajo", "Personal")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
    ) {
        SpaceH(10.dp)
        Encabezado("Nueva tarea")
        SpaceH(25.dp)

        Column(modifier = Modifier.padding(horizontal = 30.dp)) {


            Etiqueta("Nombre de la tarea")
            SpaceH(5.dp)
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    mostrarError = false
                },

                singleLine = true,
                isError = mostrarError,
                supportingText = {
                    if (mostrarError) Text("Escribe el nombre de la tarea")
                },
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
            SpaceH(15.dp)


            Etiqueta("Categoría")
            categorias.forEach { opcion ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                        .selectable(
                            selected = categoria == opcion,
                            onClick = { categoria = opcion }
                        )
                        .padding(vertical = 2.dp)
                ) {
                    RadioButton(
                        selected = categoria == opcion,
                        onClick = { categoria = opcion },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.DarkGray,
                            unselectedColor = Color.Black
                        )
                    )
                    Text(text = opcion, fontSize = 16.sp, color = Color.Black)
                }
            }
            SpaceH(15.dp)


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(2.dp, Color.Black, RectangleShape)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Prioridad alta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = prioridadAlta,
                    onCheckedChange = { prioridadAlta = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color.DarkGray
                    )
                )
            }
            SpaceH(30.dp)


            Button(
                onClick = {
                    if (nombre.isBlank()) {
                        mostrarError = true
                    } else {
                        viewModel.agregarTarea(nombre.trim(), categoria, prioridadAlta)

                        navController.popBackStack("Lista", inclusive = false)
                    }
                },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, RectangleShape)
            ) {
                Text("Guardar tarea", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = { navController.popBackStack("Lista", inclusive = false) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Cancelar", color = Color.Black)
            }
            SpaceH(20.dp)
        }
    }
}
