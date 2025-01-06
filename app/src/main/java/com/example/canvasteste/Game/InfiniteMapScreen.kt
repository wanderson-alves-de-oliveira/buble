package com.example.canvasteste.Game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
 import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
 import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
 import com.example.canvasteste.Game.model.Level
import com.example.canvasteste.Game.ui.Card
import com.example.canvasteste.Game.ui.resizeTo
import com.example.canvasteste.Game.ui.toPx
import com.example.canvasteste.R
import kotlinx.coroutines.launch
import kotlin.math.sqrt



@Composable
fun InfiniteMapScreen(navController: NavController,  context: Context) {
    var scrollOffset by remember { mutableStateOf(0f) } // Controle da rolagem
    val levelSpacing = 300f // Espaçamento entre os níveis

    val tela = Tela(context)
    val res = context.resources
    var scrollY by remember { mutableStateOf(0f) }
    val initialOffset = tela.getTamanhoTela().y*0.9f
    var distancia by remember { mutableStateOf(0f) }
    var itt by remember { mutableStateOf("") }
    var selectede  by remember { mutableStateOf(-400.dp) }


    var b = BitmapFactory.decodeResource(res, R.drawable.blue)


    var dirX :Float = 1.dp.toPx()
    var scrollXList: MutableList<Float> = mutableListOf(
        (dirX*200f), (dirX*300f),(dirX*220f),(dirX*210f),(dirX*320f),(dirX*250f),//6
        (dirX*230f),(dirX*220f),(dirX*180f),(dirX*320f),(dirX*202f),(dirX*228f),(dirX*225f),(dirX*260f),(dirX*180f),(dirX*100f),(dirX*130f)//11
        ,(dirX*225f),(dirX*225f),(dirX*225f),(dirX*265f),(dirX*255f),(dirX*250f),(dirX*180f),(dirX*160f),(dirX*140f),(dirX*170f),(dirX*150f)
        ,(dirX*120f),(dirX*100f),(dirX*180f),(dirX*200f),(dirX*190f),(dirX*190f),(dirX*215f),(dirX*130f),(dirX*150f),(dirX*200f),(dirX*240)
        ,(dirX*250),(dirX*245),(dirX*220),(dirX*230),(dirX*220),(dirX*130))

    var indexPosX by remember { mutableStateOf(0) }
    var pos by remember { mutableStateOf(0) }

    var b1 = BitmapFactory.decodeResource(res, R.drawable.red)


    val paint2 = Paint().asFrameworkPaint().apply {
        shader = BitmapShader(
            b1.resizeTo(45.dp.toPx().toInt()),
            android.graphics.Shader.TileMode.DECAL,
            android.graphics.Shader.TileMode.DECAL
        )

    }






    val paint = Paint().asFrameworkPaint().apply {
        shader = BitmapShader(
            ImageBitmap.imageResource(id = R.drawable.ma).asAndroidBitmap()
                .resizeTo(((tela.getTamanhoTela().x) * 6.5).toInt()),
            Shader.TileMode.REPEAT,
            Shader.TileMode.MIRROR
        )
    }

    var my = 0f

    // Gerar posições aleatórias para os níveis
    val levels = remember {
        List(1000) { index ->
            var xx:Float = (dirX*100f)

             if(indexPosX  < scrollXList.size ) {
                 xx = scrollXList[indexPosX]
                 indexPosX++
             }else{
                 indexPosX = 0
             }
            pos++

if(pos == 47 ){
    pos = 1
    my+= (dirX*100f)
}
            Level(
                number = index + 1,
                x = xx, // X aleatório (largura simulada)
                y = initialOffset-index * levelSpacing - my
            )
        }
    }

    Box(
        Modifier
            .fillMaxSize()


    ) {

//
//        Image(
//            painter = painterResource(id = R.drawable.ma), // Substitua com sua imagem
//            contentDescription = "Background",
//            modifier = Modifier
//                .fillMaxSize()
//                .offset { IntOffset(0, scrollOffset.toInt() % mapHeight.toInt()) },
//            contentScale = ContentScale.FillBounds
//        )
//

        Canvas(modifier = Modifier.fillMaxSize()) {


            drawIntoCanvas {
                it.save()

                it.translate(0f, (scrollOffset.toInt() ).toFloat())
                it.nativeCanvas.drawPaint(
                    paint
                )

                it.translate(0f, 0f)


                it.restore()
            }

            drawLevels(levels, scrollOffset, size.width)

            // Desenhar o mapa de níveis

        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()
    Box(
        Modifier
            .fillMaxSize()
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
                            scrollY += dist
                            distancia += dist
                            scrollOffset += dist


                        } else if (distancia > 0f) {
                            scrollY -= dist
                            distancia -= dist
                            scrollOffset -= dist

                        }
                        if (distancia < 0f) {
                            scrollY = 0f
                            distancia = 0f
                            scrollOffset = 0f

                        }
                    },
                    onDragCancel = {

                    },
                    onDragEnd = {

                    }
                )
            }
            .pointerInput(Unit) {

                var interaction: DragInteraction.Start? = null

                detectTapGestures { tapOffset ->

                    coroutineScope.launch {
                        interaction = DragInteraction.Start()
                        interaction?.run {
                            interactionSource.emit(this)

                    val tappedLevel = levels.find {
                        val adjustedY = it.y + scrollOffset
                        val isTapped = Offset(it.x, adjustedY).getDistance(tapOffset) < 50f
                        isTapped
                    }

                    itt = tappedLevel?.number.toString()
                    if (itt != null)
                        selectede = 40.dp
                        }
                    }
                }
            }
    ){


        Column(
            modifier = Modifier .offset {
                IntOffset(
                    x =selectede.toPx().toInt(),
                    y = 120.dp.toPx().toInt()
                )
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (itt != null){

                Card(b, tela.context, Modifier, "", itt+"a", true, onclick = {
                    navController.navigate("game/${Uri.encode(itt)}")
                }, onclickX = { selectede = -400.dp })

        }




        }




    }





}

private fun Offset.getDistance(): Any {
    val x = this.x
    val y = this.y
    return sqrt(x * x + y * y)
}


fun DrawScope.drawLevels(
    levels: List<Level>,
    scrollOffset: Float,
    screenWidth: Float
) {
    levels.forEach { level ->
        val adjustedY = level.y + scrollOffset

        // Ignorar se o ponto está fora da tela
        if (adjustedY >=-100 && adjustedY <= size.height.toInt() + 100) {
            // Desenhar o círculo do nível

            drawContext.canvas.save()
            drawCircle(

                color = Color.Red,
                radius = 50f,
                center = Offset(level.x.coerceIn(100f, screenWidth - 100f), adjustedY)
            )

            // Adicionar texto do número da fase
            drawContext.canvas.nativeCanvas.apply {

                drawText(
                    "  ${level.number}",
                    level.x.coerceIn(100f, screenWidth - 100f) - 50f,
                    adjustedY + 15f,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 20.sp.toPx()
                    }
                )
            }

            drawContext.canvas.restore()


        }
    }
}


fun Offset.getDistance(other: Offset): Float {
    return Math.sqrt(
        ((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)).toDouble()
    ).toFloat()
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



