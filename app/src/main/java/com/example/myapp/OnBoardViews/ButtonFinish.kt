package com.example.myapp.OnBoardViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.data.StoreBoarding
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
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        if (currentPage == lastPage) {
            OutlinedButton(onClick = {
                scope.launch {

                    store.saveBoarding(true)
                }

                navController.navigate("Home") {
                    popUpTo("OnBoarding") { inclusive = true }
                }
            }) {
                Text(
                    "Entrar",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 40.dp),
                    color = Color.Gray
                )
            }
        }
    }
}
