package com.example.myapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atom.room.components.CircleButton
import com.atom.room.components.MainIconButton
import com.atom.room.viewModel.CronometroViewModel
import com.example.myapp.R
import com.example.myapp.components.MainTextField
import com.example.myapp.components.MainTitle
import com.example.myapp.components.formatTiempo
import com.example.myapp.model.Cronos

import com.example.myapp.viewModel.CronosViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(cronometroVM: CronometroViewModel, cronosVM: CronosViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title={ MainTitle("Add Crono") },
                colors= TopAppBarDefaults.
                centerAlignedTopAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon=Icons.Default.ArrowBack) {
                        //Aqui va el codigo de atras de navigation
                    }
                }
            )
        }
    ){
        ContentAddView(cronometroVM, cronosVM)
    }
}

@Composable
fun ContentAddView(cronometroVM: CronometroViewModel, cronosVM: CronosViewModel) {
    val state = cronometroVM.state
    LaunchedEffect(state.cronometroActivo) {
        cronometroVM.cronos()
    }
    Column(
        modifier = Modifier.padding().padding(top = 30.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text= formatTiempo(cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            CircleButton(
                icon= rememberVectorPainter(Icons.Default.PlayArrow),
                enabled=!state.cronometroActivo
            ) { cronometroVM.inicia()}
                //pausar
            CircleButton(
                icon=rememberVectorPainter(
                    Icons.Default.Pause
                ),
                enabled =!state.cronometroActivo
            ) {
                cronometroVM.pausar()

            }

            //guardar
            CircleButton(
                icon=rememberVectorPainter(
                    Icons.Default.Save
                ),
                enabled = state.showSaveButton
            ) {
                cronometroVM.showTextField()
            }
        }
        if(state.showTextField){
            MainTextField(
                value=state.title,
                onValueChange = {cronometroVM.onValue(it)},
                label = "Title"
            )
            Button(onClick = {
                cronosVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = cronometroVM.tiempo))
            }) {
                Text("Guardar")
            }
            DisposableEffect(Unit) {
                onDispose {
                    cronometroVM.pausar()
                }
            }
        }
    }
}

