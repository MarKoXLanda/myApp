package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.uiApp.Detail
import com.example.myapp.uiApp.Home

@Composable
fun Navigate(){
    val navController = rememberNavController()


    NavHost(
        navController = navController, startDestination = "Home"
    ){
        composable("Home"){
            Home(navController)
        }
        composable("Detail"){
            Detail(navController)

        }
    }

}