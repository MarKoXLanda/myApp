package com.example.myapp.OnBoardViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.data.StoreBoarding
import com.example.myapp.ui.theme.Naranja
import kotlinx.coroutines.launch

@Composable
fun ButtonFinish(
    currentPage: Int,
    lastPage: Int,
    navController: NavController,
    store: StoreBoarding
) {
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .padding(horizontal = 40.dp, vertical = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (currentPage == lastPage) {
            Button(
                onClick = {
                    scope.launch {
                        store.saveBoarding(true)
                        navController.navigate("Equipos") {
                            popUpTo("OnBoarding") { inclusive = true }
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Naranja,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "¡A JUGAR!",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
