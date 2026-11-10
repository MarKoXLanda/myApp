package com.example.myapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapp.model.Tarea

class TareasViewModel : ViewModel() {


    val tareas = mutableStateListOf<Tarea>()

    private var siguienteId = 1

    fun agregarTarea(nombre: String, categoria: String, prioridadAlta: Boolean) {
        tareas.add(
            Tarea(
                id = siguienteId++,
                nombre = nombre,
                categoria = categoria,
                prioridadAlta = prioridadAlta
            )
        )
    }


    fun cambiarEstado(tarea: Tarea, terminada: Boolean) {
        val index = tareas.indexOfFirst { it.id == tarea.id }
        if (index != -1) {
            tareas[index] = tarea.copy(terminada = terminada)
        }
    }
}
