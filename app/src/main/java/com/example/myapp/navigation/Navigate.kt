package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.viewModel.TareasViewModel
import com.example.myapp.views.ListaTareasView
import com.example.myapp.views.NuevaTareaView


@Composable
fun Navigate(viewModel: TareasViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Lista") {
        composable("Lista") {
            ListaTareasView(navController, viewModel)
        }
        composable("NuevaTarea") {
            NuevaTareaView(navController, viewModel)
        }
    }
}
