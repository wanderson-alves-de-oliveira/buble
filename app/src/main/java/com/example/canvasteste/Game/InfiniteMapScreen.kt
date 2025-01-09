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


@Composable
fun InfiniteMapScreen(navController: NavController, context: Context) {
    var scrollOffset by remember { mutableStateOf(0f) } // Controle da rolagem
    var edd by remember { mutableStateOf(0f) } // Controle da rolagem
    val tela = Tela(context)
    val res = context.resources
    var scrollY by remember { mutableStateOf(0f) }
    val initialOffset = tela.getTamanhoTela().y+100.dp.toPx()
    var distancia by remember { mutableStateOf(0f) }
    var itt by remember { mutableStateOf("") }
    var selectede by remember { mutableStateOf(100.dp) }
    var selectedew by remember { mutableStateOf(false) }
    var pointer by remember { mutableStateOf(Offset(0f, 0f)) }
    var ultimaFase by remember { mutableStateOf(0) }
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
        (dirX * 170f),
        (dirX * 110f),//17
        (dirX * 110f),
        (dirX * 225f),
        (dirX * 225f),//20
        (dirX * 225f),
        (dirX * 225f),
        (dirX * 270f),//23
        (dirX * 235f),
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
        (dirX * 200),//39
        (dirX * 180),
        (dirX * 245),
        (dirX * 220),
        (dirX * 230),
        (dirX * 270),
        (dirX * 230),//45
        (dirX * 230),
        (dirX * 230),
        (dirX * 230),
        (dirX * 130),
        (dirX * 110),
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
    // Gerar posições aleatórias para os níveis
    val levels = remember {
        List(50) { index ->
            var xx: Float = (dirX * 100f)
            if (indexPosX < scrollXList.size) {
                xx = scrollXList[indexPosX]
                indexPosX++
            } else {
                indexPosX = 0
            }
            pos++
            if (pos == 50) {
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
                            edd += 1f
                            pointer = Offset(tapOffset.x, tapOffset.y)
                            if (!selectedew) {
                                var index = ((scrollOffset) / (100.dp.toPx())).toInt()
                                var lista = mutableListOf<Level>()
                                lista.addAll(levels)
                                if (index > 50) {
                                    var vox = index / 50
                                    for (i in 0..lista.size - 1) {
                                        var l: Level = lista[0]
                                        var novoNum = (vox * 50) + i
                                        var novo = Level(
                                            novoNum,
                                            l.x,
                                            initialOffset - (novoNum * 100.dp.toPx())
                                        )
                                        lista.add(1, novo)
                                        lista.removeAt(0)
                                        lista.sortBy { it.number }
                                    }
                                    index -= (vox * 50)
                                }
                                for (i in 0..index - 1) {
                                    var l: Level = lista[0]
                                    var novoNum = lista[lista.lastIndex].number + 1
                                    var novo = Level(
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
                                }
                            }
                            scrollY -= 1f
                        }
                    }
                }
            }
    ) {
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {
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
                    var dist = yfinalP - yfinal
                    yfinalP -= (dist / 10).dp.toPx()
                    if (dist < 10) {
                        fimp = true
                        yfinalP = yfinal.toFloat()
                    }
                }

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
        Box(
            Modifier
                .fillMaxSize()
                .offset {
                    IntOffset(
                        x = ((tela.getTamanhoTela().x / 2) - 170.dp
                            .toPx()
                            .toInt()).toInt(),
                        y = 0.dp
                            .toPx()
                            .toInt()
                    )
                }
        ) {
            if (selectedew) {
                Card(
                    b,
                    tela.context,
                    modifier = Modifier,
                    "",
                    itt,
                    selectedew,
                    "Derrube todas as bolas",
                    onclick = {
                        navController.navigate("game/${itt}")
                    },
                    onclickX = {
                        yfinalP = fy
                        selectedew = false
                    })
                if (!(edd.toInt() % 2 == 0)) edd++
            }
        }
        /////////////////////
    }
}

fun DrawScope.drawLevels(
    lista: List<Level>,
    scrollOffsetM: Float,
    screenWidth: Float,
    res: Resources,
    initialOffset: Float
) {




    var scrollOffset = scrollOffsetM

    var index = ((scrollOffset) / (100.dp.toPx())).toInt()
    var levels = mutableListOf<Level>()
    levels.addAll(lista)
    if (index > 50) {
        var vox = index / 50
        for (i in 0..levels.size - 1) {
            var l: Level = levels[0]
            var novoNum = (vox * 50) + i
            var novo = Level(novoNum, l.x, initialOffset - (novoNum * 100.dp.toPx()))
            levels.add(1, novo)
            levels.removeAt(0)
            levels.sortBy { it.number }
        }
        index -= (vox * 50)
    }
    for (i in 0..index - 1) {
        var l: Level = levels[0]
        var novoNum = levels[levels.lastIndex].number + 1
        var novo = Level(novoNum, l.x, initialOffset - (novoNum * 100.dp.toPx()))
        levels.add(1, novo)
        levels.removeAt(0)
        levels.sortBy { it.number }
    }

    roud(levels,scrollOffsetM)

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
                color =  Color(80, 89, 196, 255),
                radius = 50f,
                center = Offset(level.x.coerceIn(100f, screenWidth - 100f), adjustedY)
            )

            drawCircle(
                color =  Color(134, 196, 80, 255),
                radius = 40f,
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

fun DrawScope.roud(
    levels: List<Level>,  scrollOffsetM: Float

) {
    drawContext.canvas.nativeCanvas.apply {



        val pincel = android.graphics.Paint()
        pincel.color = android.graphics.Color.rgb(112,128,144)
        pincel.setStrokeWidth(78f)

        this.save()





        for (ii in 0..levels.size-2) {

            this.drawLine(
                levels[ii].x.toFloat(), levels[ii].y.toFloat() + scrollOffsetM,
                levels[ii+1].x.toFloat(), levels[ii+1].y.toFloat()+ scrollOffsetM,
                pincel
            )



        }


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


