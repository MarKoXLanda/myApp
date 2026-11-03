package com.example.myapp.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.OnBoardViews.MainOnBoarding
import com.example.myapp.data.StoreBoarding
import com.example.myapp.views.HomeView
import kotlinx.coroutines.flow.first

@Composable
fun NavManager() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = remember { StoreBoarding(context) }


    var inicio by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val terminado = dataStore.getStoreBoarding.first()
        inicio = if (terminado) "Home" else "OnBoarding"
    }

    if (inicio == null) {

        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Red)
        }
    } else {
        NavHost(navController = navController, startDestination = inicio!!) {
            composable("OnBoarding") {
                MainOnBoarding(navController, dataStore)
            }
            composable("Home") {
                HomeView(navController, dataStore)
            }
        }
    }
}
