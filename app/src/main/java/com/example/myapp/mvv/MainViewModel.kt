package com.example.myapp.mvv
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel(){
    var resultState by mutableStateOf("")
    var cargando by mutableStateOf(false)

    fun fetchData(){
        viewModelScope.launch {
            LlamarAPI()
            try {
                cargando=true
                LlamarAPI()
            }catch (e: Exception){
                println("Error: ${e.message}")
            }finally {
                cargando = false
            }
        }
    }

    private suspend fun LlamarAPI(){
        val result = withContext(Dispatchers.IO){
            delay(5000)
            "Respuesta"
        }
        resultState = result
    }
}