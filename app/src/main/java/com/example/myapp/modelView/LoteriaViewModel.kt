package com.example.myapp.modelView

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class LoteriaViewModel: ViewModel(){
    private val _loteriaNumbers = mutableStateOf(emptyList<Int>())
    val loteriaNumber : State<List<Int>> = _loteriaNumbers

    fun generadorNumeros(){
        _loteriaNumbers.value = (1..60).shuffled().take(6).sorted()
    }
}