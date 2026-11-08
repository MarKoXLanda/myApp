package com.example.myapp.OnBoardViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.myapp.data.PageData
import com.example.myapp.data.StoreBoarding
import com.example.myapp.ui.theme.GrisTexto
import com.example.myapp.ui.theme.Naranja
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<PageData>,
    pagerState: PagerState,
    navController: NavController,
    store: StoreBoarding,
    modifier: Modifier = Modifier
    )
    {
    Box(modifier= modifier.safeDrawingPadding()){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 50.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    BalonAnimado(
                        tipo = item[page].animacion,
                        modifier = Modifier
                            .size(220.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Text(text=item[page].title,
                        modifier=Modifier.padding(top=40.dp),
                        color=Naranja,
                        fontWeight = FontWeight.Black,
                        fontSize=24.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(text=item[page].description,
                        modifier=Modifier.padding(top=24.dp),
                        color=GrisTexto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }
            Pageindicator(item.size,pagerState.currentPage)
        }
        Box(modifier= Modifier.align(Alignment.BottomCenter)){
            ButtonFinish(pagerState.currentPage, item.size - 1, navController, store)
        }
    }

}
