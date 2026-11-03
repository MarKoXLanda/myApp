package com.example.myapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.OnBoardViews.LoaderData
import com.example.myapp.R
import com.example.myapp.data.StoreBoarding
import kotlinx.coroutines.launch

@Composable
fun HomeView(navController: NavController, store: StoreBoarding) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoaderData(
            modifier = Modifier.size(220.dp),
            image = R.raw.redcar
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "HOME",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Fin se acabo el onboarding",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))


        OutlinedButton(onClick = {
            scope.launch { store.saveBoarding(false) }
            navController.navigate("OnBoarding") {
                popUpTo("Home") { inclusive = true }
            }
        }) {
            Text("Borrar", color = Color.Gray)
        }
    }
}
