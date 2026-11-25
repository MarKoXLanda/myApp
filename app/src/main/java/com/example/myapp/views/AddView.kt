package com.example.myapp.views

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.atom.room.components.MainIconButton
import com.example.myapp.components.MainTitle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(){
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
        ContentAddView()
    }
}

fun ContentAddView(){

}