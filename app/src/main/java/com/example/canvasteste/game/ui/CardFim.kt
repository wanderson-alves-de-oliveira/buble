package com.example.canvasteste.game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela
import com.example.canvasteste.R


@Composable
internal fun CardFim(
    img: Bitmap,
    context: Context,
    modifier: Modifier,
    textButton: String,
    textCard: String,
    imageBt: Int = R.drawable.seta,
    onclick: () -> Unit
) {
    val tela = Tela(context)
//    var sons: SonsDoCard = SonsDoCard(context)
//    var tocou1: Boolean by remember { mutableStateOf(false) }
//    var tocou2: Boolean by remember { mutableStateOf(false) }
//    var tocou3: Boolean by remember { mutableStateOf(false) }

    val textMeasurer = rememberTextMeasurer()
    val res = context.resources
    Box {

        val paint4 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                img.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }


        val yfinal = ((tela.getTamanhoTela().y / 2) - 100.dp.toPx()).toInt()
        val fy = 1200.dp.toPx()
        var yfinalP: Float by remember { mutableFloatStateOf(fy) }
        var fimp: Boolean by remember { mutableStateOf(false) }


        if (yfinalP > yfinal) {
            val dist = yfinalP - yfinal
            yfinalP -= (dist / 8).dp.toPx()

            if (dist < 10) {
                yfinalP = yfinal.toFloat()
                fimp = true
            }
        }


        var time: Int by remember { mutableIntStateOf(0) }
        var xvc: Float by remember { mutableFloatStateOf(0f) }
        var xvc2: Float by remember { mutableFloatStateOf(0f) }
        var xvc3: Float by remember { mutableFloatStateOf(0f) }

        var xvcP: Float by remember { mutableFloatStateOf(20f) }
        var yvcP: Float by remember { mutableFloatStateOf(0f) }

        var xvcP2: Float by remember { mutableFloatStateOf(85f) }
        var yvcP2: Float by remember { mutableFloatStateOf(20f) }

        var xvcP3: Float by remember { mutableFloatStateOf(170f) }
        var yvcP3: Float by remember { mutableFloatStateOf(0f) }


        val msgem2 = remember {
            textMeasurer.measure(
                text = "Excelente",
                style = TextStyle(fontSize = 22.sp, color = Color(0xFFFFFFFF)),
                overflow = TextOverflow.Ellipsis
            )
        }


        Column(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = ((tela.getTamanhoTela().x / 2) - 120.dp.toPx()).toInt(),
                        y = yfinalP.toInt()
                    )
                },
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Canvas(modifier = Modifier.size(230.dp, 210.dp)) {


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



                    val b = BitmapFactory.decodeResource(res, R.drawable.estrela)
                    val bf = BitmapFactory.decodeResource(res, R.drawable.estrelafosca)


                    val paint5 = Paint().asFrameworkPaint().apply {
                        shader = BitmapShader(
                            b.resizeTo(45.dp.toPx().toInt()),
                            android.graphics.Shader.TileMode.DECAL,
                            android.graphics.Shader.TileMode.DECAL
                        )

                    }

                    val paint6 = Paint().asFrameworkPaint().apply {
                        shader = BitmapShader(
                            bf.resizeTo(45.dp.toPx().toInt()),
                            android.graphics.Shader.TileMode.DECAL,
                            android.graphics.Shader.TileMode.DECAL
                        )

                    }

                    drawIntoCanvas {
                        it.save()
                        it.translate((10.dp.toPx()), (20.dp.toPx()))

                        it.nativeCanvas.drawPaint(
                            paint6
                        )

                        it.restore()
                    }


                    drawIntoCanvas {
                        it.save()
                        it.translate((75.dp.toPx()), (0.dp.toPx()))
                        it.scale(1.5f, 1.5f)

                        it.nativeCanvas.drawPaint(
                            paint6
                        )

                        it.restore()
                    }

                    drawIntoCanvas {
                        it.save()
                        it.translate((160.dp.toPx()), (20.dp.toPx()))

                        it.nativeCanvas.drawPaint(
                            paint6
                        )

                        it.restore()
                    }

                    if (fimp) {
                        //  Thread.sleep(1)
                        time++



                        if (time > 1) {


                            xvc = if (xvc < 1f) {


                                xvc + 0.2f

                            } else {

                                1f
                            }

                            xvcP = if (xvcP > 10f) xvcP - 2f else 10f
                            yvcP = if (yvcP < 20f) yvcP + 2f else 20f


                            drawIntoCanvas {
                                it.save()
                                it.translate((xvcP.dp.toPx()), (yvcP.dp.toPx()))
                                it.scale(xvc, xvc)

                                it.nativeCanvas.drawPaint(
                                    paint5
                                )

                                it.restore()
                            }
                        }
                        if (time > 11) {


                            xvc2 = if (xvc2 < 1.5f) {
                                xvc2 + 0.2f
                            } else {
//                                if (!tocou2) {
//                                    tocou2 = true
//                                    sons.OnPlim2()
//                                }

                                1.5f
                            }


                            xvcP2 = if (xvcP2 > 75f) xvcP2 - 2f else 75f
                            yvcP2 = if (yvcP2 > 0f) yvcP2 - 2f else 0f



                            drawIntoCanvas {
                                it.save()
                                it.translate((xvcP2.dp.toPx()), (yvcP2.dp.toPx()))

                                it.scale(xvc2, xvc2)
                                it.nativeCanvas.drawPaint(
                                    paint5

                                )

                                it.restore()


                            }
                        }
                        if (time > 21) {


                            xvc3 = if (xvc3 < 1f) {
                                xvc3 + 0.2f
                            } else {
//                                if (!tocou3) {
//                                    tocou3 = true
//                                    sons.OnPlim3()
//                                }

                                1f
                            }


                            xvcP3 = if (xvcP3 > 160f) xvcP3 - 2f else 160f
                            yvcP3 = if (yvcP3 < 20f) yvcP3 + 2f else 20f



                            drawIntoCanvas {

                                it.save()
                                it.translate((xvcP3.dp.toPx()), (yvcP3.dp.toPx()))

                                it.scale(xvc3, xvc3)

                                it.nativeCanvas.drawPaint(
                                    paint5
                                )

                                it.restore()
                            }
                        }
                    }
                    drawIntoCanvas {
                        it.save()
                        it.translate((30.dp.toPx()), 115.dp.toPx())

                        it.nativeCanvas.drawPaint(
                            paint4
                        )

                        it.restore()

                    }



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

                    drawIntoCanvas {
                        it.save()
                        it.translate((30.dp.toPx()), 115.dp.toPx())

                        it.nativeCanvas.drawPaint(
                            paint4
                        )

                        it.restore()

                    }
                    drawText(
                        textLayoutResult = msgem2,
                        topLeft = Offset(80.dp.toPx(), 105.dp.toPx())
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
                    255,
                    255,
                    255
                )
                pincel.textSize = 20.sp.toPx()

                drawIntoCanvas {
                    it.save()

                    it.nativeCanvas.drawText("Nível $textCard", 70.dp.toPx(), 190.dp.toPx(), pincel)

                    it.restore()

                }




            }


            Box(modifier = modifier) {
                Spacer(modifier = modifier.padding(5.dp))

                Botao(
                    context = context,
                    onClick = onclick,
                    text = textButton,
                    imageBt = imageBt
                )

            }


        }


    }


}



