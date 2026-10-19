package com.example.myapp.mvv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.LecturaModel
import com.example.myapp.data.SensorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

data class SensorUiState(
    val actual: Double = 0.0,
    val humedad: Double = 0.0,
    val promedio: Double = 0.0,
    val lecturas: List<Double> = emptyList(),
    val monitoreando: Boolean = false
)

class SensorViewModel(
    private val repo: SensorRepository = SensorRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(SensorUiState())
    val uiState: StateFlow<SensorUiState> = _uiState.asStateFlow()

    //alertas, con buffer para que no se trabe con el snackbar
    private val _alertas = MutableSharedFlow<String>(
        extraBufferCapacity = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val alertas: SharedFlow<String> = _alertas.asSharedFlow()

    private val _contador = MutableStateFlow<Int?>(null)
    val contador: StateFlow<Int?> = _contador.asStateFlow()

    //Actividad 7
    private val _umbral = MutableStateFlow(35f)
    val umbral: StateFlow<Float> = _umbral.asStateFlow()

    //Actividad 3
    val temperaturaCaliente: StateFlow<Double> = repo.temperaturaFlow()
        .map { redondear(it) }
        .retry()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    private var jobSensor: Job? = null
    private var jobContador: Job? = null

    fun iniciar() {
        if (jobSensor?.isActive == true) return
        _uiState.update { it.copy(monitoreando = true) }

        val temperatura = repo.temperaturaFlow()
            .retry(3) { e ->   //Actividad 6
                _alertas.emit("Reintentando: ${e.message}")
                delay(500)
                true
            }
            .map { redondear(it) }
            .filter { it > 25 }   //Actividad 2
            .distinctUntilChanged()

        //Actividad 4
        val sensores = combine(temperatura, repo.humedadFlow()) { temp, hum ->
            LecturaModel(temperatura = temp, humedad = redondear(hum))
        }

        jobSensor = viewModelScope.launch {
            combine(sensores, _umbral) { lectura, umbral -> lectura to umbral }
                .onEach { (lectura, umbral) ->
                    if (lectura.temperatura > umbral) {
                        _alertas.emit("Temperatura alta: ${lectura.temperatura} °C (umbral ${"%.1f".format(umbral)})!")
                    }
                }
                .map { it.first }
                .distinctUntilChanged()
                .catch { e -> _alertas.emit("Error: ${e.message}") }
                .onCompletion { _uiState.update { it.copy(monitoreando = false) } }
                .collect { lectura ->
                    _uiState.update { estado ->
                        val nuevas = (estado.lecturas + lectura.temperatura).takeLast(10)
                        estado.copy(
                            actual = lectura.temperatura,
                            humedad = lectura.humedad,
                            lecturas = nuevas,
                            promedio = nuevas.average()
                        )
                    }
                }
        }
    }

    fun detener() {
        jobSensor?.cancel()
        _uiState.update { it.copy(monitoreando = false) }
    }

    fun cambiarUmbral(valor: Float) {
        _umbral.value = valor
    }

    fun iniciarContador(segundos: Int = 10) {
        jobContador?.cancel()
        jobContador = viewModelScope.launch {
            repo.cuentaRegresiva(segundos)
                .onCompletion { _contador.value = null }
                .collect { _contador.value = it }
        }
    }

    private fun redondear(valor: Double): Double = Math.round(valor * 10) / 10.0
}
