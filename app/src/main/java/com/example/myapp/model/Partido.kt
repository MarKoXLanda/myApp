package com.example.myapp.model

data class Partido(
    val local: String = "",
    val visita: String = "",
    val puntosLocal: Int = 0,
    val puntosVisita: Int = 0,
    val faltasLocal: Int = 0,
    val faltasVisita: Int = 0,
    val cuarto: Int = 1,
    val tiempo: Int = 600
)
