package com.example.myapp.OnBoardViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapp.data.PageData
import com.example.myapp.data.StoreBoarding
import com.example.myapp.ui.theme.Negro
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn  (ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding){
    val items= ArrayList<PageData>()

    items.add(
        PageData(
            ANIM_BOTE,
            title="Bienvenido a Basket Score",
            description = "Lleva el marcador de tus partidos de basketball desde tu celular"
        )
    )

    items.add(
        PageData(
            ANIM_GIRO,
            title="Puntos y faltas",
            description = "Suma tiros libres, dobles y triples  y lleva las faltas de cada equipo"
        )
    )

    items.add(
        PageData(
            ANIM_CANASTA,
            title="Reloj y cuartos",
            description = "Controla el reloj de 10 minutos y los 4 cuartos. Si cierras la app, el partido se queda guardado"
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0

    )

    OnBoardingPager(
        item =items, pagerState = pagerState,
        navController = navController, store = store,
        modifier = Modifier
            .fillMaxSize()
            .background(Negro)
    )

}
