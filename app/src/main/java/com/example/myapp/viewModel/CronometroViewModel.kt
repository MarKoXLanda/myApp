package com.atom.room.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.repository.CronosRepository
import com.example.myapp.state.CronosState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronometroViewModel
    @Inject constructor(private val reposiroty: CronosRepository)
    : ViewModel(){
        var state by mutableStateOf(CronosState())
            private set
        var cronoJob by mutableStateOf<Job?>(null)
            private set
        var tiempo by mutableStateOf(0L)
            private set

    //Funcion de actualizacion
    fun getCronosById(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            reposiroty.getCronosById(id).collect {
                item->
                if(item != null){
                    tiempo = item.crono
                    state =state.copy(title=item.title)
                }
                else{
                    Log.d("Error:","Objeto no es nulo")
                }
            }
        }
    }

    fun onValue(value:String){
        state=state.copy(title=value)
    }

    fun inicia(){
        state=state.copy(
            cronometroActivo = true
        )
    }

    fun pausar(){
        state=state.copy(
            cronometroActivo = false,
            showSaveButton=false,
            showTextField = false,
            title=""
        )
    }

    fun showTextField(){
        state=state.copy(
            showTextField = true
        )
    }

    fun cronos(){
        if(state.cronometroActivo) {
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    tiempo += 1000
                }
            }
        }
            else{
                cronoJob?.cancel()
            }
        }
    }

