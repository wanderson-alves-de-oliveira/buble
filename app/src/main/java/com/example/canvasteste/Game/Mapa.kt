package com.example.canvasteste.Game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Build
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
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.Card
import com.example.canvasteste.R;
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Mapa(navController: androidx.navigation.NavController,context: Context,modifier: Modifier = Modifier) {
    val scrollAmount = 0.2f

    val tela = Tela(context)
    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }


    var scrollX by remember { mutableStateOf(0f) }

    var distancia by remember { mutableStateOf(0f) }
    var subir: Boolean by remember { mutableStateOf(false) }


    BoxWithConstraints (modifier = modifier.pointerInput(Unit) {
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


                if(dragAmount.y>0 ){
                    scrollX += 5f
                    distancia+= 5f
                }else if(distancia>0f){
                    scrollX -=5f
                    distancia-= 5f
                }


            },
            onDragCancel = {
                coroutineScope.launch {
                    interaction?.run {


                    }
                }
            },
            onDragEnd = {
                coroutineScope.launch {
                    interaction?.run {


                        interactionSource.emit(DragInteraction.Stop(this))
                    }
                }
            }
        )
    }) {



        val viewPort = remember {Viewport(maxWidth, maxHeight)    }
        val di = rememberDI(viewPort)

        LaunchedEffect(key1 = Unit) {
            di.timeManager.deltaTime.collect { deltaTime ->

            }
        }



        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.ma).asAndroidBitmap()
                    .resizeTo(((tela.getTamanhoTela().x)*6.5).toInt()),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(0f, scrollX.dp.toPx())
                it.nativeCanvas.drawPaint(
                    paint
                )
                it.translate(0f, 0f)
            }
        }





val tela = Tela(context)
        if (subir) {
            Box(
                modifier = Modifier.fillMaxSize()
            )
            {
                Column(
                    modifier = modifier
                        .size(250.dp, 310.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val res = tela.context.resources
                    var b = BitmapFactory.decodeResource(res, R.drawable.blue)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        Card(
                            b,
                            tela.context,
                            modifier = modifier,
                            "",
                            "Nivel 1",
                            true,
                            "TELA DO MAPA",
                            onclick = {
                                navController.navigate("game")
                            })
                    }
                }
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



