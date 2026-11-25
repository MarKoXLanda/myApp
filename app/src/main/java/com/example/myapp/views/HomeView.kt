package com.example.myapp.views

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.airbnb.lottie.animation.content.Content
import com.atom.room.components.FloatButton
import com.example.myapp.components.MainTitle
import com.example.myapp.viewModel.CronosViewodel
import me.saket.swipe.SwipeAction
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.items


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(cronosVM: CronosViewodel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title={MainTitle(title="Crono App")},
                colors= TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {

            }
        }
    ){
       ContentHomeView(it, cronosVM)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, cronosVM: CronosViewodel) {
    Column(
        modifier = Modifier.padding(it)
    ) {
        val cronosList by cronosVM.cronosList.collectAsState()
        LazyColumn {
            items(cronosList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(
                        image = Icons.Default.Delete
                    ),
                    background = Color.Red,
                    onSwipe = { cronosVM.deleteCrono(cronos = item) }
                )

            }
        }
    }
}