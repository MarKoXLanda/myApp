package com.example.myapp.OnBoardViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.data.PageData
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<PageData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
){
    Box(modifier=modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager (state = pagerState) {page ->
                Column(
                    modifier = Modifier.padding(60.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                     LoaderData(modifier = Modifier.size(200.dp).fillMaxWidth().align(alignment = Alignment.CenterHorizontally),
                         item[page].image)
                    Text(text=item[page].titulo,modifier = Modifier.padding(top=50.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }


            }
            PagerIndicator(item.size,pagerState.currentPage)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            ButtonFinish(pagerState.currentPage)
        }
    }

}