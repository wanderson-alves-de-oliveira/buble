package com.example.canvasteste.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.canvasteste.R
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela
import com.example.canvasteste.game.model.BackgroundL
import com.example.canvasteste.game.model.Casa
import com.example.canvasteste.game.model.Level
import com.example.canvasteste.game.model.Ponto
import com.example.canvasteste.game.ui.Card
import com.example.canvasteste.game.ui.resizeTo
import com.example.canvasteste.game.ui.toPx
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.sqrt


@SuppressLint("AutoboxingStateCreation")
@Composable
fun InfiniteMapScreen(navController: NavController, context: Context) {
    var scrollOffset by remember { mutableStateOf(0f) } // Controle da rolagem
    var edd by remember { mutableStateOf(0f) } // Controle da rolagem
    val tela = Tela(context)
    val res = context.resources
    var scrollY by remember { mutableStateOf(0f) }
    val initialOffset = tela.getTamanhoTela().y + 100.dp.toPx()
    var itt by remember { mutableStateOf("") }
    var selectede by remember { mutableStateOf(100.dp) }
    var selectedew by remember { mutableStateOf(false) }
    var pointer by remember { mutableStateOf(Offset(0f, 0f)) }
    val ultimaFase by remember { mutableStateOf(0) }
    BitmapFactory.decodeResource(res, R.drawable.blue)
    val yfinal = ((tela.getTamanhoTela().y / 2) - 100.dp.toPx()).toInt()
    val fy = 1000.dp.toPx()
    var yfinalP: Float by remember { mutableStateOf(fy) }
    var fimp: Boolean by remember { mutableStateOf(false) }
    val dirX: Float = 1.dp.toPx()
    val scrollXList: MutableList<Float> = mutableListOf(
        (dirX * 200f),
        (dirX * 290f),
        (dirX * 300f),
        (dirX * 220f),
        (dirX * 140f),
        (dirX * 120f),//6
        (dirX * 190f),
        (dirX * 300f),
        (dirX * 320f),
        (dirX * 290f),
        (dirX * 210f),
        (dirX * 180f),
        (dirX * 220f),
        (dirX * 290f),
        (dirX * 280f),
     //   (dirX * 260f),
//
//        (dirX * 110f),//17
//        (dirX * 110f),
//        (dirX * 225f),
//        (dirX * 225f),//20
//        (dirX * 225f),
//        (dirX * 225f),
//        (dirX * 270f),//23
//        (dirX * 235f),
//        (dirX * 160f),
//        (dirX * 140f),
//        (dirX * 170f),
//        (dirX * 150f),
//        (dirX * 120f),
//        (dirX * 100f),
//        (dirX * 180f),
//        (dirX * 200f),
//        (dirX * 190f),
//        (dirX * 190f),
//        (dirX * 215f),
//        (dirX * 130f),
//        (dirX * 150f),
//        (dirX * 200f),
//        (dirX * 200),//39
//        (dirX * 180),
//        (dirX * 245),
//        (dirX * 220),
//        (dirX * 230),
//        (dirX * 270),
//        (dirX * 230),//45
//        (dirX * 230),
//        (dirX * 230),
//        (dirX * 230),
//        (dirX * 130),
//        (dirX * 110),
    )



    var indexPosX by remember { mutableStateOf(0) }
    var pos by remember { mutableStateOf(0) }
    if (ultimaFase > 0)
        scrollOffset = (initialOffset - (ultimaFase + 5) * (dirX * 100)) * -1
    val paint = Paint().asFrameworkPaint().apply {
        shader = BitmapShader(
            ImageBitmap.imageResource(id = R.drawable.ma).asAndroidBitmap()
                .resizeTo(((tela.getTamanhoTela().x) * 2.1).toInt()),
            Shader.TileMode.DECAL,
            Shader.TileMode.MIRROR
        )
    }
    var my = 0f

 val limitFases:Int = 15


    // Gerar posições aleatórias para os níveis
    val levels = remember {
        List(limitFases) { index ->
            var xx: Float = (dirX * 100f)
            if (indexPosX < scrollXList.size) {
                xx = scrollXList[indexPosX]
                indexPosX++
            } else {
                indexPosX = 0
            }
            pos++
            if (pos == limitFases) {
                pos = 1
                my += (dirX * 100f)
            }
            Level(
                number = index + 1,
                x = xx,
                //   y = initialOffset - index * levelSpacing - my
                y = initialOffset - (index + 1) * (dirX * 100)
            )
        }
    }
    val interactionSource = remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                var interaction: DragInteraction.Start?
                detectDragGestures(
                    onDragStart = {
                        coroutineScope.launch {
                            interaction = DragInteraction.Start()
                            interaction?.run {
                                interactionSource.emit(this)
                            }
                        }
                    },
                    onDrag = { _: PointerInputChange, dragAmount: Offset ->
//                        val dist = dragAmount.getDistance()
//                        if (dragAmount.y > 0) {
//                            scrollY += dist
//                            distancia += dist
//                            scrollOffset += dist
//                        } else if (distancia > 0f) {
//                            scrollY -= dist
//                            distancia -= dist
//                            scrollOffset -= dist
//                        }
//                        if (distancia < 0f) {
//                            scrollY = 0f
//                            distancia = 0f
//                            scrollOffset = 0f
//                        }
                        scrollOffset += dragAmount.y
                        scrollY+= dragAmount.y
                    },
                    onDragCancel = {
                    },
                    onDragEnd = {
                    }
                )
            }
            .pointerInput(Unit) {
                var interaction: DragInteraction.Start?
                detectTapGestures { tapOffset ->
                    coroutineScope.launch {
                        interaction = DragInteraction.Start()
                        interaction?.run {
                            interactionSource.emit(this)
                            edd += 1f
                            pointer = Offset(tapOffset.x, tapOffset.y)
                            if (!selectedew) {
                                var index = ((scrollOffset) / (100.dp.toPx())).toInt()
                                val lista = mutableListOf<Level>()
                                lista.addAll(levels)

                                for (i in 0..<index) {
                                    val l: Level = lista[0]
                                    val novoNum = lista[lista.lastIndex].number + 1
                                    val novo = Level(
                                        novoNum,
                                        l.x,
                                        initialOffset - (novoNum * 100.dp.toPx())
                                    )
                                    lista.add(1, novo)
                                    lista.removeAt(0)
                                    lista.sortBy { it.number }
                                }
                                val tappedLevel = lista.find {
                                    val adjustedY =
                                        (initialOffset - (it.number) * (dirX * 100)) + scrollOffset
                                    val isTapped =
                                        Offset(it.x, adjustedY).getDistance(tapOffset) < 50f
                                    isTapped
                                }
                                itt = tappedLevel?.number.toString()
                                if (itt != "null") {
                                    selectede = 40.dp
                                    selectedew = true
                                }
                            } else {
                                if (pointer.x.toDp() >= 275.dp && pointer.x.toDp() <= 316.dp &&
                                    pointer.y.toDp() >= 193.dp && pointer.y.toDp() <= 240.dp
                                ) {
                                    yfinalP = fy
                                    selectedew = false
                                } else if (pointer.x.toDp() >= 156.dp && pointer.x.toDp() <= 240.dp &&
                                    pointer.y.toDp() >= 451.dp && pointer.y.toDp() <= 494.dp
                                ) {

                                    navController.navigate("game/${itt}")

                                }
                            }
                            scrollY -= 1f
                        }
                    }
                }
            }
    ) {

        Box(
            Modifier
                .fillMaxSize()
        ) {


            coroutineScope.run {
                Canvas(modifier = Modifier.fillMaxSize()) {





                    //
                    drawRoundRect(
                        color = Color(0xFF171616),
                        topLeft = Offset(0f, 0f),
                        cornerRadius = CornerRadius(45.0f),
                        style = Fill,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                        blendMode = DefaultBlendMode
                    )
                    drawIntoCanvas {
                        it.save()
                        it.translate(0f, (scrollOffset.toInt()).toFloat())
                        it.nativeCanvas.drawPaint(
                            paint
                        )
                        it.translate(0f, 0f)
                        it.restore()
                    }
                    drawLevels(levels, scrollOffset, size.width, res, initialOffset)
                    if (selectedew) {
                        if (yfinalP > yfinal) {
                            val dist = yfinalP - yfinal
                            yfinalP -= (dist / 10).dp.toPx()
                            if (dist < 10) {
                                fimp = true
                                yfinalP = yfinal.toFloat()
                            }
                        }
                    }

//                    drawContext.canvas.nativeCanvas.apply {
//                        drawText(
//                            "  ${pointer.x.toDp()} ${pointer.y.toDp()}",
//                            0f,
//                            220f,
//                            android.graphics.Paint().apply {
//                                color = android.graphics.Color.WHITE
//                                textSize = 20.sp.toPx()
//                            }
//                        )
//                    }
//                    drawCircle(
//                        color = Color(80, 89, 196, 255),
//                        radius = 50f,
//                        center = Offset( pointer.x,  pointer.y)
//                    )
//



                }
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .offset {
                        IntOffset(
                            x = ((tela.getTamanhoTela().x / 2) - 150.dp.toPx()).toInt(),
                            y = (tela.getTamanhoTela().x / 2).toInt()
                        )
                    }
            ) {
                val coroutineScope2 = rememberCoroutineScope()
                coroutineScope2.run {

                    if (selectedew) {
                        Card(
                            tela.context,
                            modifier = Modifier,
                            itt,
                            "Derrube todas as bolas"
                        )
                        if (edd.toInt() % 2 != 0) edd++
                    }
                }
            }
            /////////////////////
        }


    }

}

fun DrawScope.drawLevels(
    lista: List<Level>,
    scrollOffsetM: Float,
    screenWidth: Float,
    res: Resources,
    initialOffset: Float
) {

    var index = ((scrollOffsetM) / (100.dp.toPx())).toInt()
    val levels = mutableListOf<Level>()
    levels.addAll(lista)



        for (i in 0..<index) {
            val l: Level = levels[0]
            val novoNum = levels[levels.lastIndex].number + 1
            val novo = Level(novoNum, l.x, initialOffset - (novoNum * 100.dp.toPx()))
            levels.add(1, novo)
            levels.removeAt(0)
            levels.sortBy { it.number }
        }



    roud(levels, scrollOffsetM,res)


    levels.forEach { level ->


        val adjustedY = level.y + scrollOffsetM
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
                color = Color(147, 199, 232, 255),
                radius = 100f,
                center = Offset(level.x.coerceIn(100f, screenWidth - 100f), adjustedY)
            )
            drawCircle(
                color = Color(134, 196, 80, 255),
                radius = 90f,
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


    houses(scrollOffsetM,res,initialOffset)
}

fun DrawScope.roud(
    levels: List<Level>, scrollOffsetM: Float, res: Resources

) {




    drawContext.canvas.nativeCanvas.apply {
        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.rgb(112, 128, 144)
        pincel.strokeWidth = 100f
        this.save()
        for (ii in 0..levels.size - 2) {

            val adjustedY = levels[ii].y + scrollOffsetM

            if (adjustedY >= 200 && adjustedY <= size.height.toInt() + 100) {

                this.drawLine(
                    levels[ii].x, levels[ii].y + scrollOffsetM,
                    levels[ii + 1].x, levels[ii + 1].y + scrollOffsetM,
                    pincel
                )
            }

        }
        this.restore()
    }
}


fun DrawScope.houses(
    scrollOffsetM: Float,
    res: Resources,
    initialOffset: Float
) {

        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.rgb(112, 128, 144)
        pincel.strokeWidth = 78f


        var c1 = BitmapFactory.decodeResource(res, R.drawable.casaia)
        c1 = Bitmap.createScaledBitmap(c1, 255.dp.toPx().toInt(), 255.dp.toPx().toInt(), true)


        var c2 = BitmapFactory.decodeResource(res, R.drawable.casaib)
        c2 = Bitmap.createScaledBitmap(c2, 265.dp.toPx().toInt(), 265.dp.toPx().toInt(), true)


        var c3 = BitmapFactory.decodeResource(res, R.drawable.casaic)
        c3 = Bitmap.createScaledBitmap(c3, 255.dp.toPx().toInt(), 255.dp.toPx().toInt(), true)



        var c4 = BitmapFactory.decodeResource(res, R.drawable.casaid)
        c4 = Bitmap.createScaledBitmap(c4, 255.dp.toPx().toInt(), 255.dp.toPx().toInt(), true)


        var c5 = BitmapFactory.decodeResource(res, R.drawable.casaie)
        c5 = Bitmap.createScaledBitmap(c5, 255.dp.toPx().toInt(), 255.dp.toPx().toInt(), true)

        var casas:MutableList<Casa> = mutableListOf(
            Casa(c1,Offset( -50.dp.toPx(),1400f),1f,1),
            Casa(c2,Offset( 200.dp.toPx(),600f),1f,2),
            Casa(c3,Offset( -40.dp.toPx(),-200f),1f,3),
            Casa(c4,Offset( 230.dp.toPx(),-1000f),1f,4),
            Casa(c5,Offset( 0.dp.toPx(),-2000f),1f,5))


    var index = (scrollOffsetM / ( 300.dp.toPx())     ).toInt()



    for (i in 0..<index) {
        val c: Casa = casas[0]
        val novoNum = casas[casas.lastIndex].numero + 1
        val novoOffset = Offset(c.offset.x, initialOffset- ((novoNum * 300.dp.toPx()) +100.dp.toPx()))
        val novo = Casa( c.bitmap, novoOffset,c.escala,novoNum)
        casas.removeAt(0)
        casas.add(0, novo)

        casas.sortBy { it.numero }
    }


    drawContext.canvas.nativeCanvas.apply {


            this.save()
            for (i in 0..<casas.size) {


                val adjustedY = casas[i].offset.y + scrollOffsetM

                if (adjustedY >= -400 && adjustedY <= size.height.toInt() + 100) {
                    drawBitmap(
                        casas[i].bitmap,
                        casas[i].offset.x,
                        adjustedY,
                        pincel
                    )
                }

            }

            this.restore()

    }
}













fun Offset.getDistance(other: Offset): Float {
    return sqrt(
        ((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)).toDouble()
    ).toFloat()
}

//
//fun calcularAnguloRotacao(pontoA: Ponto, pontoB: Ponto): Double {
//    // Calculando a diferença entre as coordenadas x e y
//    val deltaX = pontoB.x - pontoA.x
//    val deltaY = pontoB.y - pontoA.y
//
//    // Calculando o ângulo em radianos usando a função arcotangente
//    var angulo = atan2(deltaY, deltaX)
//
//    // Convertendo para graus (se necessário)
//    angulo = Math.toDegrees(angulo)
//
//    // Ajustando o ângulo de acordo com o quadrante
//    // ... (implementar a lógica para ajustar o ângulo de acordo com o quadrante)
//
//    return angulo
//}
