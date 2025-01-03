package com.example.canvasteste.Game.ui
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
import com.example.canvasteste.Game.di.engeni.TimeManager
import com.example.canvasteste.R
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun Background(timeManager: TimeManager) {
    val scrollAmount = 0.2f


    BoxWithConstraints {
//        var scrollX by remember { mutableStateOf(0f) }
//
//        LaunchedEffect(key1 = Unit) {
//            timeManager.deltaTime.collect { it ->
//                scrollX -= it * scrollAmount
//            }
//        }
        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.background).asAndroidBitmap()
                    .resizeTo(maxHeight.toPx().toInt()),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            drawIntoCanvas {
//                it.translate(0f, scrollX.dp.toPx())
//                it.nativeCanvas.drawPaint(
//                    paint
//                )
//                it.translate(0f, 0f)
//            }
//        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(0f, 0f)
                it.nativeCanvas.drawPaint(
                    paint
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