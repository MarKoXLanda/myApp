package com.example.myapp.OnBoardViews

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.Naranja


@Composable
fun Pageindicator(size: Int, currentPage: Int){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(size){
            Indicator(isSelect = it==currentPage)
        }
    }
}

@Composable
fun Indicator(isSelect : Boolean){
    val ancho by animateDpAsState(if (isSelect) 32.dp else 12.dp, label = "ancho")
    Box(
        modifier = Modifier.padding(5.dp).height(10.dp).width(ancho).clip(CircleShape)
            .background(color = if (isSelect) Naranja else Color.DarkGray)
    ) { }
}
