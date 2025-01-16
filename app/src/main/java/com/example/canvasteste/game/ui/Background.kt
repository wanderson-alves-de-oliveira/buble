package com.example.canvasteste.game.ui
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
 import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela
 import com.example.canvasteste.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun Background(tela: Tela, i: Int) {


    BoxWithConstraints {
//        var scrollX by remember { mutableStateOf(0f) }
//

        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.gatinhasfofasc).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val paint2 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.gatinhasfofasa).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.4).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val paint3 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.caofofosc).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val paint4 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.coelhos).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }


        val paint5 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.urso).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val paint6 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.pinguim).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val paint7 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.cao).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }
        val paint8= Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.ovelha).asAndroidBitmap()
                    .resizeTo((tela.getTamanhoTela().y*1.18).toInt()),
                Shader.TileMode.DECAL,
                Shader.TileMode.MIRROR
            )
        }

        val lista = mutableListOf(paint,paint2,paint3,paint4,paint5,paint6,paint7,paint8)

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(0f, 0f)
                it.nativeCanvas.drawPaint(
                    lista[generateFixedNumber(i)]
                )
                it.translate(0f, 0f)
            }
        }


    }
}

fun Bitmap.resizeTo(maxHeight: Int): Bitmap {
    val sourceWidth: Int = width
    val sourceHeight: Int = height
    val sourceRatio = sourceWidth.toFloat() / sourceHeight.toFloat()
    val targetWidth = (maxHeight.toFloat() * sourceRatio).toInt()
    return Bitmap.createScaledBitmap(this, targetWidth, maxHeight, true)
}
@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) {
        toPx()
    }
}



fun generateFixedNumber(input: Int): Int {
    return input % 8 // Garante um número entre 0 e 7
}








