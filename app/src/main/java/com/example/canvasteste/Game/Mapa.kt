package com.example.canvasteste.Game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Build
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvasteste.Game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.Card
import com.example.canvasteste.Game.ui.resizeTo
import com.example.canvasteste.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Mapa (
    navController: androidx.navigation.NavController,
    context: Context,
    modifier: Modifier = Modifier
) {
    val scrollAmount = 0.2f

    val tela = Tela(context)
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }

    var xy by remember { mutableStateOf(Offset(0f,0f)) }

    var scrollX by remember { mutableStateOf(0f) }

    var distancia by remember { mutableStateOf(0f) }
    var subir: Boolean by remember { mutableStateOf(false) }
    var tr: String by remember { mutableStateOf("") }
    val res = context.resources
    var distancia2 by remember { mutableStateOf(0f) }
    var yc by remember { mutableStateOf(0) }
    var yc2 by remember { mutableStateOf(1) }



    val textMeasurer = rememberTextMeasurer()



    var msgem = remember {
        textMeasurer.measure(
            text = distancia2.toString(),
            style = TextStyle(fontSize = 16.sp, color = Color(0xFFFAEFEF)),
            overflow = TextOverflow.Ellipsis
        )
    }




    BoxWithConstraints(modifier = modifier
        .pointerInput(Unit) {
            var interaction: DragInteraction.Start? = null
            detectDragGestures(
                onDragStart = {
                    coroutineScope.launch {
                        interaction = DragInteraction.Start()
                        interaction?.run {


                            interactionSource.emit(this)
                        }
                    }
                },
                onDrag = { change: PointerInputChange, dragAmount: Offset ->

                    var dist = dragAmount.getDistance()



                    if (dragAmount.y > 0) {

                        subir = true
                    }


                    subir = true
                    if (dragAmount.y > 0) {
                        scrollX += dist
                        distancia += dist
                        distancia2 += dist



                    } else if (distancia > 0f) {
                        scrollX -= dist
                        distancia -= dist
                        distancia2 -= dist

                    }
                    if (distancia < 0f) {
                        scrollX = 0f
                        distancia = 0f
                        distancia2 = 0f

                    }


                    msgem = textMeasurer.measure(
                        text =distancia2.toInt().toString()+"  "+ tela.getTamanhoTela().y.toInt()/5,
                        style = TextStyle(fontSize = 16.sp, color = Color(0xFFFAEFEF)),
                        overflow = TextOverflow.Ellipsis
                    )



                },
                onDragCancel = {
                    coroutineScope.launch {
                        interaction?.run {
                            subir = true

                        }
                    }
                },
                onDragEnd = {
                    coroutineScope.launch {
                        interaction?.run {
                            subir = true

                            interactionSource.emit(DragInteraction.Stop(this))
                        }
                    }
                }
            )
        }

        .pointerInteropFilter {


            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    xy = Offset(it.x, it.y)

                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    subir = true
                    true
                }

                MotionEvent.ACTION_UP -> {

                    subir = true
                    true
                }

                else -> false
            }
            true
        }



    ) {


        val viewPort = remember { Viewport(maxWidth, maxHeight) }
        val di = rememberDI(viewPort)

        LaunchedEffect(key1 = Unit) {
            di.timeManager.deltaTime.collect { deltaTime ->

            }
        }

        var b1 = BitmapFactory.decodeResource(res, R.drawable.red)

        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.ma).asAndroidBitmap()
                    .resizeTo(((tela.getTamanhoTela().x) * 6.5).toInt()),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }

        val paint2 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                b1.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }

var yyu =  b1.resizeTo(45.dp.toPx().toInt()).width

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.save()
                it.translate(0f, scrollX.dp.toPx())
                it.nativeCanvas.drawPaint(
                    paint
                )
                it.translate(0f, 0f)


                it.restore()
            }

            for(i in 0..10) {
                var xc = if(i%2==0) tela.getTamanhoTela().x*0.7f else  tela.getTamanhoTela().x*0.3f

                drawIntoCanvas {
                    it.save()
                    it.translate(xc, (( ((yc * i )* -( tela.getTamanhoTela().y*0.2f)))+tela.getTamanhoTela().y*0.9f) +( distancia.dp.toPx()) )

                    if(distancia2 >= tela.getTamanhoTela().y/5){
                        yc2++
                        distancia2=0f
                    }
                    it.nativeCanvas.drawPaint(
                        paint2
                    )

                    it.restore()
                }
            }


            for(i in 0..10) {
                var xc = if(i%2==0) tela.getTamanhoTela().x*0.7f else  tela.getTamanhoTela().x*0.3f

                drawIntoCanvas {
                    it.save()
                    it.translate(xc, (( ((yc2 * i )* -( tela.getTamanhoTela().y*0.2f)))+tela.getTamanhoTela().y*0.9f) +( distancia.dp.toPx()) )

                    if(distancia2 >= tela.getTamanhoTela().y/5){
                        yc++
                        distancia2=0f
                    }
                    it.nativeCanvas.drawPaint(
                        paint2
                    )

                    it.restore()
                }
            }



            drawText(
                textLayoutResult = msgem,
                topLeft = Offset(80.dp.toPx(), 105.dp.toPx())
            )



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



