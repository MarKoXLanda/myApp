package com.example.myapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.random.Random

data class LecturaModel(
    val temperatura: Double = 0.0,
    val humedad: Double = 0.0,
    val hora: Long = System.currentTimeMillis()
)

class SensorRepository {

    //Flow frio
    fun temperaturaFlow(): Flow<Double> = flow {
        while (true) {
            //Actividad 6
            if (Random.nextInt(100) < 5) {
                throw IllegalStateException("Falla en el sensor de temperatura")
            }
            val lectura = 20.0 + Random.nextDouble() * 20.0 // 20 - 40 °C
            emit(lectura)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default) //Actividad 5

    //Actividad 4 sensor de humedad
    fun humedadFlow(): Flow<Double> = flow {
        while (true) {
            val lectura = 30.0 + Random.nextDouble() * 50.0 // 30 - 80 %
            emit(lectura)
            delay(1500)
        }
    }.flowOn(Dispatchers.Default)

    fun cuentaRegresiva(desde: Int): Flow<Int> = flow {
        for (i in desde downTo 0) {
            emit(i)
            delay(1000)
        }
    }
}
