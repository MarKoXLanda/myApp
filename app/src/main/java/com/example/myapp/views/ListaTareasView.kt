package com.example.myapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.components.Encabezado
import com.example.myapp.components.SpaceH
import com.example.myapp.components.TareaCard
import com.example.myapp.viewModel.TareasViewModel


@Composable
fun ListaTareasView(navController: NavController, viewModel: TareasViewModel) {
    Scaffold(
        containerColor = Color.LightGray,
        floatingActionButton = {

            FloatingActionButton(
                onClick = { navController.navigate("NuevaTarea") },
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                shape = RectangleShape,
                modifier = Modifier.border(2.dp, Color.Black, RectangleShape)
            ) {
                Text("+", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            SpaceH(10.dp)
            Encabezado("Mis tareas")
            SpaceH(15.dp)

            if (viewModel.tareas.isEmpty()) {
                Text(
                    text = "No hay tareas\n Presiona + para agregar una nueva tarea",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 40.dp)
                )
            } else {
                LazyColumn {
                    items(viewModel.tareas, key = { it.id }) { tarea ->
                        TareaCard(
                            tarea = tarea,
                            onCambiarEstado = { terminada ->
                                viewModel.cambiarEstado(tarea, terminada)
                            }
                        )
                    }

                    item { SpaceH(90.dp) }
                }
            }
        }
    }
}
