package com.example.canvasteste.Game

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Shader
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.drawText
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.sqrt


@Composable
fun InfiniteMapScreen(navController: NavController, context: Context) {
    var scrollOffset by remember { mutableStateOf(0f) } // Controle da rolagem
    val levelSpacing = 300f // Espaçamento entre os níveis
    var edd by remember { mutableStateOf(0f) } // Controle da rolagem

    val tela = Tela(context)
    val res = context.resources
    var scrollY by remember { mutableStateOf(0f) }
    val initialOffset = tela.getTamanhoTela().y * 0.9f
    var distancia by remember { mutableStateOf(0f) }
    var itt by remember { mutableStateOf("") }
    var selectede by remember { mutableStateOf(100.dp) }
    var selectedew by remember { mutableStateOf(false) }
    var pointer by remember { mutableStateOf(Offset(0f, 0f)) }


    var b = BitmapFactory.decodeResource(res, R.drawable.blue)

    val yfinal = ((tela.getTamanhoTela().y / 2) - 100.dp.toPx()).toFloat().toInt()
    val fy = 1000.dp.toPx()
    var yfinalP: Float by remember { mutableStateOf(fy) }
    var fimp: Boolean by remember { mutableStateOf(false) }

    val dirX: Float = 1.dp.toPx()
    var scrollXList: MutableList<Float> = mutableListOf(
        (dirX * 200f),
        (dirX * 300f),
        (dirX * 220f),
        (dirX * 210f),
        (dirX * 320f),
        (dirX * 250f),//6
        (dirX * 230f),
        (dirX * 220f),
        (dirX * 180f),
        (dirX * 320f),
        (dirX * 202f),
        (dirX * 228f),
        (dirX * 225f),
        (dirX * 260f),
        (dirX * 180f),
        (dirX * 100f),
        (dirX * 130f)//11
        ,
        (dirX * 225f),
        (dirX * 225f),
        (dirX * 225f),
        (dirX * 265f),
        (dirX * 255f),
        (dirX * 250f),
        (dirX * 180f),
        (dirX * 160f),
        (dirX * 140f),
        (dirX * 170f),
        (dirX * 150f),
        (dirX * 120f),
        (dirX * 100f),
        (dirX * 180f),
        (dirX * 200f),
        (dirX * 190f),
        (dirX * 190f),
        (dirX * 215f),
        (dirX * 130f),
        (dirX * 150f),
        (dirX * 200f),
        (dirX * 240),
        (dirX * 250),
        (dirX * 245),
        (dirX * 220),
        (dirX * 230),
        (dirX * 220),
        (dirX * 130)
    )

    var indexPosX by remember { mutableStateOf(0) }
    var pos by remember { mutableStateOf(0) }

    var b1 = BitmapFactory.decodeResource(res, R.drawable.red)


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
        List(400) { index ->
            var xx: Float = (dirX * 100f)

            if (indexPosX < scrollXList.size) {
                xx = scrollXList[indexPosX]
                indexPosX++
            } else {
                indexPosX = 0
            }


            pos++

            if (pos == 47) {
                pos = 1
                my += (dirX * 100f)
            }
            Level(
                number = index + 1,
                x = xx,
                y = initialOffset - index * levelSpacing - my
            )
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
                            edd+=1f

                            pointer = Offset(tapOffset.x, tapOffset.y)
                            if (!selectedew) {
                                val tappedLevel = levels.find {
                                    val adjustedY = it.y + scrollOffset
                                    val isTapped =
                                        Offset(it.x, adjustedY).getDistance(tapOffset) < 50f
                                    isTapped
                                }

                                itt = tappedLevel?.number.toString()
                                if (itt != null && itt != "null") {
                                    selectede = 40.dp
                                    selectedew = true
                                }
                            } else {

                                if (pointer.x.toDp() >= 275.dp && pointer.x.toDp() <= 316.dp &&
                                    pointer.y.toDp() >= 249.dp && pointer.y.toDp() <= 296.dp
                                ) {

                                    yfinalP = fy
                                    selectedew = false
                                } else if (pointer.x.toDp() >= 156.dp && pointer.x.toDp() <= 246.dp &&
                                    pointer.y.toDp() >= 526.dp && pointer.y.toDp() <= 573.dp
                                ) {


                                    yfinalP = fy
                                    selectedew = false
                                    navController.navigate("game/${itt}")
                                }

                            }
                            scrollY -= 1f
                        }
                    }
                }


            }
    ) {


//TODO TESTES




    }







    Box(
        Modifier
            .fillMaxSize()


    ) {








  Canvas(modifier = Modifier.fillMaxSize()) {


            drawIntoCanvas {
                it.save()

                it.translate(0f, (scrollOffset.toInt()).toFloat())
                it.nativeCanvas.drawPaint(
                    paint
                )

                it.translate(0f, 0f)


                it.restore()
            }

            drawLevels(levels, scrollOffset, size.width, res)

            if (selectedew) {


                if (yfinalP > yfinal) {
                    var dist = yfinalP - yfinal
                    yfinalP -= (dist / 10).dp.toPx()

                    if (dist < 10) {
                        fimp = true
                        yfinalP=yfinal.toFloat()
                    }
                }


                //  val coroutineScopex = rememberCoroutineScope().async {

             //    roundrect(levels, scrollOffset, tela, res, itt, yfinalP)
            //    roundrectPainel(levels, scrollOffset, tela, res, itt, yfinalP)

                // }


//TODO TESTE
//    drawCircle(
//
//        color = Color.Green,
//        radius = 50f,
//        center = pointer, 1f)
//
//    drawContext.canvas.nativeCanvas.apply {
//        drawText(
//            "x:${pointer.x.toDp()} y:${pointer.y.toDp()}",
//            100.dp.toPx(),
//            100.dp.toPx(),
//            android.graphics.Paint().apply {
//                color = android.graphics.Color.BLACK
//                textSize = 25.sp.toPx()
//            }
//        )
//    }
            }

        }

     if (selectedew) {
        Card(b, tela.context, modifier = Modifier, "", itt, selectedew, onclick = {


            navController.navigate("game/${itt}")
        }, onclickX = { })

         if(!(edd.toInt()%2==0))edd++

             }



    }




}


fun DrawScope.drawLevels(
    levels: List<Level>,
    scrollOffset: Float,
    screenWidth: Float,
    res: Resources,
) {
    levels.forEach { level ->
        val adjustedY = level.y + scrollOffset
        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.argb(
            255,
            (175 * Math.random()).toInt() + 20,
            (105 * Math.random()).toInt() + 80,
            (175 * Math.random()).toInt() + 80
        )

        var b4 = BitmapFactory.decodeResource(res, R.drawable.estrela)
        b4 = Bitmap.createScaledBitmap(b4, 25.dp.toPx().toInt(), 25.dp.toPx().toInt(), true)
        // Ignorar se o ponto está fora da tela
        if (adjustedY >= -100 && adjustedY <= size.height.toInt() + 100) {
            // Desenhar o círculo do nível

            drawContext.canvas.save()
            drawCircle(

                color = Color.Red,
                radius = 50f,
                center = Offset(level.x.coerceIn(100f, screenWidth - 100f), adjustedY)
            )


            // Adicionar texto do número da fase
            drawContext.canvas.nativeCanvas.apply {


                var tamLetra = 20.sp.toPx()
                if (level.number > 99) tamLetra = 15.sp.toPx()
                drawText(
                    "  ${level.number}",
                    level.x.coerceIn(100f, screenWidth - 100f) - 50f,
                    adjustedY + 15f,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.WHITE
                        textSize = tamLetra
                    }
                )




                drawBitmap(
                    b4,
                    level.x.coerceIn(100f, screenWidth - 100f) - 30.dp.toPx(),
                    adjustedY,
                    pincel
                )

                drawBitmap(
                    b4,
                    level.x.coerceIn(100f, screenWidth - 100f) - 10.dp.toPx(),
                    adjustedY + 10.dp.toPx(),
                    pincel
                )

                drawBitmap(
                    b4,
                    level.x.coerceIn(100f, screenWidth - 100f) + 10.dp.toPx(),
                    adjustedY,
                    pincel
                )

            }

            drawContext.canvas.restore()


        }
    }
}

fun DrawScope.roundrect(
    levels: List<Level>,
    scrollOffset: Float,
    tela: Tela,
    res: Resources,
    itt: String,
    yfinalP: Float
) {


    drawContext.canvas.nativeCanvas.apply {

        this.save()


        var b = BitmapFactory.decodeResource(res, R.drawable.green)
        b = Bitmap.createScaledBitmap(b, 45.dp.toPx().toInt(), 45.dp.toPx().toInt(), true)


        var b2 = BitmapFactory.decodeResource(res, R.drawable.red)
        b2 = Bitmap.createScaledBitmap(b2, 60.dp.toPx().toInt(), 60.dp.toPx().toInt(), true)


        var b3 = BitmapFactory.decodeResource(res, R.drawable.blue)
        b3 = Bitmap.createScaledBitmap(b3, 45.dp.toPx().toInt(), 45.dp.toPx().toInt(), true)


        var b4 = BitmapFactory.decodeResource(res, R.drawable.estrela)
        b4 = Bitmap.createScaledBitmap(b4, 55.dp.toPx().toInt(), 55.dp.toPx().toInt(), true)


        var b5 = BitmapFactory.decodeResource(res, R.drawable.seta)
        b5 = Bitmap.createScaledBitmap(b5, 50.dp.toPx().toInt(), 35.dp.toPx().toInt(), true)

        this.translate((tela.getTamanhoTela().x / 2) - 100.dp.toPx(), yfinalP)





        drawRoundRect(
            color = Color(0xFF486CBB),
            topLeft = Offset(-10.dp.toPx(), 5.dp.toPx()),
            size = Size(width = 230.dp.toPx(), height = 210.dp.toPx()),
            cornerRadius = CornerRadius(80.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )

        drawRoundRect(
            color = Color(0xFF346ADA),
            topLeft = Offset(-3.dp.toPx(), 5.dp.toPx()),
            size = Size(width = 220.dp.toPx(), height = 200.dp.toPx()),
            cornerRadius = CornerRadius(80.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )



        drawRoundRect(
            color = Color(0xFF75ACDA),
            topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
            size = Size(width = 205.dp.toPx(), height = 80.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )


        drawRoundRect(
            color = Color(Color(255, 220, 200, 150).toArgb()),
            topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
            size = Size(width = 205.dp.toPx(), height = 75.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )



        drawRoundRect(
            color = Color(0xFF75ACDA),
            topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
            size = Size(width = 205.dp.toPx(), height = 80.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )


        drawRoundRect(
            color = Color(Color(255, 220, 200, 150).toArgb()),
            topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
            size = Size(width = 205.dp.toPx(), height = 75.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )
        drawRoundRect(
            color = Color(0xFF75ACDA),
            topLeft = Offset(50.dp.toPx(), 165.dp.toPx()),
            size = Size(width = 110.dp.toPx(), height = 40.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )


        drawRoundRect(
            color = Color(Color(232, 2, 73, 150).toArgb()),
            topLeft = Offset(50.dp.toPx(), 160.dp.toPx()),
            size = Size(width = 110.dp.toPx(), height = 45.dp.toPx()),
            cornerRadius = CornerRadius(45.0f),
            style = Fill,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
            blendMode = DefaultBlendMode
        )


        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.argb(
            255,
            (175 * Math.random()).toInt() + 20,
            (105 * Math.random()).toInt() + 80,
            (175 * Math.random()).toInt() + 80
        )






       // drawContext.canvas.nativeCanvas.apply {


            this.save()


            this.translate(
                (tela.getTamanhoTela().x / 2) - 135.dp.toPx(),
                (tela.getTamanhoTela().y / 2) - 165.dp.toPx()
            )


            drawRoundRect(
                color = Color(0xFF75ACDA),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 90.dp.toPx(), height = 40.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )


            drawRoundRect(
                color = Color(Color(231, 8, 227, 155).toArgb()),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 90.dp.toPx(), height = 45.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )

            this.restore()
     //   }




      //  drawContext.canvas.nativeCanvas.apply {


            this.save()


            this.translate(
                (tela.getTamanhoTela().x / 2) - 15.dp.toPx(),
                (tela.getTamanhoTela().y / 2) - 440.dp.toPx()
            )


            drawRoundRect(
                color = Color(0xFFF6B20C),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 40.dp.toPx(), height = 40.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )


            drawRoundRect(
                color = Color(Color(225, 14, 14, 155).toArgb()),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 40.dp.toPx(), height = 45.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )

            this.restore()
    //    }



        drawBitmap(b, 10.dp.toPx(), 20.dp.toPx(), pincel)

        drawBitmap(b2, 80.dp.toPx(), -10.dp.toPx(), pincel)

        drawBitmap(b3, 160.dp.toPx(), 20.dp.toPx(), pincel)

        drawBitmap(b5, 80.dp.toPx(), 235.dp.toPx(), pincel)




        drawText(
            "Derrube todas as bolas",
            20.dp.toPx(),
            112.dp.toPx(),
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 18.sp.toPx()
            }
        )





        drawText(
            "Nível ${itt}",
            70.dp.toPx(),
            192.dp.toPx(),
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 22.sp.toPx()
            }
        )
        drawText(
            "X",
            194.dp.toPx(),
            -18.dp.toPx(),
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 22.sp.toPx()
            }
        )
        drawBitmap(b4, 6.dp.toPx(), 130.dp.toPx(), pincel)

        this.restore()
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



fun DrawScope.roundrectPainel(
    levels: List<Level>,
    scrollOffset: Float,
    tela: Tela,
    res: Resources,
    itt: String,
    yfinalP: Float
) {


    drawContext.canvas.nativeCanvas.apply {

        this.save()


        var b = BitmapFactory.decodeResource(res, R.drawable.painel)
        b = Bitmap.createScaledBitmap(b, 250.dp.toPx().toInt(), 330.dp.toPx().toInt(), true)



        this.translate((tela.getTamanhoTela().x / 2) - 130.dp.toPx(), yfinalP-70.dp.toPx())




        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.argb(
            255,
            (175 * Math.random()).toInt() + 20,
            (105 * Math.random()).toInt() + 80,
            (175 * Math.random()).toInt() + 80
        )



        this.save()




        drawBitmap(b, 10.dp.toPx(), 20.dp.toPx(), pincel)



        drawText(
            "Derrube todas as bolas",
            50.dp.toPx(),
            192.dp.toPx(),
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 18.sp.toPx()
            }
        )





        drawText(
            "Nível ${itt}",
            90.dp.toPx(),
            272.dp.toPx(),
            android.graphics.Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 22.sp.toPx()
            }
        )


        this.restore()
    }

}
