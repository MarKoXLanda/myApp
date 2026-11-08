package com.example.myapp.OnBoardViews

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import com.example.myapp.ui.theme.Duela
import com.example.myapp.ui.theme.Naranja


const val ANIM_BOTE = 0
const val ANIM_GIRO = 1
const val ANIM_CANASTA = 2

@Composable
fun BalonAnimado(tipo: Int, modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "balon")


    val bote by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(550, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "bote"
    )


    val giro by transition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "giro"
    )


    val tiro by transition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "tiro"
    )

    Canvas(modifier = modifier) {
        when (tipo) {
            ANIM_BOTE -> dibujarBote(bote)
            ANIM_GIRO -> dibujarBalon(center, size.minDimension * 0.35f, giro)
            else -> dibujarCanasta(tiro)
        }
    }
}



fun DrawScope.dibujarBalon(c: Offset, r: Float, grados: Float, alpha: Float = 1f) {
    val grosor = r * 0.07f
    val linea = Stroke(width = grosor)

    rotate(grados, pivot = c) {
        drawCircle(Naranja, radius = r, center = c, alpha = alpha)

        val circulo = Path().apply { addOval(Rect(c, r)) }
        clipPath(circulo) {
            drawLine(Color.Black, Offset(c.x, c.y - r), Offset(c.x, c.y + r), grosor, alpha = alpha)
            drawLine(Color.Black, Offset(c.x - r, c.y), Offset(c.x + r, c.y), grosor, alpha = alpha)
            drawArc(
                Color.Black, startAngle = -50f, sweepAngle = 100f, useCenter = false,
                topLeft = Offset(c.x - 2f * r, c.y - r), size = Size(1.6f * r, 2f * r),
                style = linea, alpha = alpha
            )
            drawArc(
                Color.Black, startAngle = 130f, sweepAngle = 100f, useCenter = false,
                topLeft = Offset(c.x + 0.4f * r, c.y - r), size = Size(1.6f * r, 2f * r),
                style = linea, alpha = alpha
            )
        }
        drawCircle(Color.Black, radius = r, center = c, style = linea, alpha = alpha)
    }
}

private fun DrawScope.dibujarBote(p: Float) {
    val r = size.minDimension * 0.18f
    val piso = size.height * 0.88f
    val arriba = size.height * 0.12f + r
    val abajo = piso - r
    val y = arriba + (abajo - arriba) * p

    drawLine(Duela, Offset(size.width * 0.1f, piso), Offset(size.width * 0.9f, piso), strokeWidth = r * 0.15f)

    val sombraAncho = r * (0.8f + 1.0f * p)
    drawOval(
        Color.White.copy(alpha = 0.05f + 0.15f * p),
        topLeft = Offset(center.x - sombraAncho, piso - r * 0.12f),
        size = Size(sombraAncho * 2f, r * 0.24f)
    )

    dibujarBalon(Offset(center.x, y), r, grados = p * 90f)
}

private fun DrawScope.dibujarCanasta(p: Float) {
    val w = size.width
    val h = size.height
    val r = size.minDimension * 0.08f

    val aroX = w * 0.66f
    val aroY = h * 0.40f
    val aroMedio = w * 0.14f

    drawRect(Color.White, topLeft = Offset(aroX - w * 0.2f, h * 0.08f), size = Size(w * 0.4f, h * 0.26f))
    drawRect(
        Naranja, topLeft = Offset(aroX - w * 0.08f, h * 0.17f), size = Size(w * 0.16f, h * 0.12f),
        style = Stroke(width = r * 0.25f)
    )

    val bola: Offset
    var alpha = 1f
    if (p < 0.6f) {
        val t = p / 0.6f
        val p0 = Offset(w * 0.12f, h * 0.88f)
        val p1 = Offset(w * 0.35f, -h * 0.25f)
        val p2 = Offset(aroX, aroY - r)
        val u = 1 - t
        bola = Offset(
            u * u * p0.x + 2 * u * t * p1.x + t * t * p2.x,
            u * u * p0.y + 2 * u * t * p1.y + t * t * p2.y
        )
    } else {
        val t = (p - 0.6f) / 0.4f
        bola = Offset(aroX, (aroY - r) + (h * 0.95f - aroY) * t)
        alpha = 1f - t
    }
    dibujarBalon(bola, r, grados = p * 540f, alpha = alpha)

    val redAbajo = aroY + h * 0.2f
    for (i in 0..4) {
        val xArriba = aroX - aroMedio + (2 * aroMedio) * i / 4f
        val xAbajo = aroX - aroMedio * 0.6f + (1.2f * aroMedio) * i / 4f
        drawLine(Color.White, Offset(xArriba, aroY), Offset(xAbajo, redAbajo), strokeWidth = r * 0.12f)
    }

    drawLine(Naranja, Offset(aroX - aroMedio, aroY), Offset(aroX + aroMedio, aroY), strokeWidth = r * 0.35f)
}
