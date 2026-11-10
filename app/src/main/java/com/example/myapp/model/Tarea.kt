package com.example.myapp.model

data class Tarea(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val prioridadAlta: Boolean,
    val terminada: Boolean = false
)
