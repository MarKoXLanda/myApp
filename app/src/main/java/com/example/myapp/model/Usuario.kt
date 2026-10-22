package com.example.myapp.model

data class Usuario(
    val usuario: String = "",
    val pass: String = "",
    val nombre: String = "",
    val correo: String = "",
    val telefono: String = "",
    val completo: Boolean = false
)
