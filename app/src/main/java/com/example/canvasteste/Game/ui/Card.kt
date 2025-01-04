package com.example.canvasteste.Game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvasteste.Game.di.engeni.ferramentas.ConvertBitimap
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.R


@RequiresApi(Build.VERSION_CODES.S)
@Composable
internal fun Card(
    img: Bitmap,
    context: Context,
    modifier: Modifier,
    textButton: String,
    textCard: String,
    fim: Boolean,
    msg: String = "",
    onclick: () -> Unit
) {
    val context: Context = context
    var fim = fim
    var tela: Tela = Tela(context)
    Box {


        val res = context.resources
        var b = BitmapFactory.decodeResource(res, R.drawable.estrela)
        //  b = Bitmap.createScaledBitmap(b, 45, 45, false)

//
//        val res = context.resources
        var b2 = ConvertBitimap.getBitmap(context, R.drawable.bola, 0, 0, 0, false)
//        b2 = Bitmap.createScaledBitmap(b2, 45, 45, false)

        var bb: Int by remember { mutableStateOf( R.drawable.estrela) }

        var Red: Int by remember { mutableStateOf((0..255).random()) }
        var Green: Int by remember { mutableStateOf((0..255).random()) }
        var Blue: Int by remember { mutableStateOf((0..255).random()) }


        var Red2: Int by remember { mutableStateOf((0..255).random()) }
        var Green2: Int by remember { mutableStateOf((0..255).random()) }
        var Blue2: Int by remember { mutableStateOf((0..255).random()) }

        var Red3: Int by remember { mutableStateOf((0..255).random()) }
        var Green3: Int by remember { mutableStateOf((0..255).random()) }
        var Blue3: Int by remember { mutableStateOf((0..255).random()) }

        var time: Int by remember { mutableStateOf(0) }
        var xvc: Float by remember { mutableStateOf(0f) }
        var xvc2: Float by remember { mutableStateOf(0f) }
        var xvc3: Float by remember { mutableStateOf(0f) }

        var xvcP: Float by remember { mutableStateOf(20f) }
        var yvcP: Float by remember { mutableStateOf(0f) }

        var xvcP2: Float by remember { mutableStateOf(85f) }
        var yvcP2: Float by remember { mutableStateOf(20f) }

        var xvcP3: Float by remember { mutableStateOf(170f) }
        var yvcP3: Float by remember { mutableStateOf(0f) }

        if(msg!="") bb=R.drawable.bola else bb=R.drawable.estrela

        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                (ConvertBitimap.getBitmap(context,bb, Red, Green, Blue, true)).resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }


        val paint2 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                (ConvertBitimap.getBitmap(context, bb, Red2, Green2, Blue2, true)).resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }

        val paint3 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                (ConvertBitimap.getBitmap(context, bb, Red3, Green3, Blue3, true)).resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }
        val paint4 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                img.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.DECAL,
                android.graphics.Shader.TileMode.DECAL
            )

        }

        val textMeasurer = rememberTextMeasurer()
        val result = remember {
            textMeasurer.measure(
                text = textCard,
                style = TextStyle(fontSize = 22.sp, color = Color(0xFFFAEFEF)),
                overflow = TextOverflow.Ellipsis
            )
        }

        val msgem = remember {
            textMeasurer.measure(
                text = msg,
                style = TextStyle(fontSize = 20.sp, color = Color(0xFFFAEFEF)),
                overflow = TextOverflow.Ellipsis
            )
        }

        val yfinal = ((tela.getTamanhoTela().y / 2) - 100.dp.toPx()).toFloat().toInt()
        val fy = 1200.dp.toPx()
        var yfinalP: Float by remember { mutableStateOf(fy) }
        var fimp: Boolean by remember { mutableStateOf(false) }


        if (yfinalP > yfinal) {
            var dist = yfinalP - yfinal
            yfinalP -= (dist / 10).dp.toPx()

            if (dist < 10) {
                fimp = true
            }
        }

        Column(
            modifier = Modifier
                .size(250.dp, 310.dp)
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
                    color = Color(0xFF7898DC),
                    topLeft = Offset(-10.dp.toPx(), 5.dp.toPx()),
                    size = Size(width = 230.dp.toPx(), height = 210.dp.toPx()),
                    cornerRadius = CornerRadius(80.0f),
                    style = Fill,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                    blendMode = DefaultBlendMode
                )



                drawRoundRect(
                    color = Color(0xFF6893EA),
                    topLeft = Offset(-3.dp.toPx(), 5.dp.toPx()),
                    size = Size(width = 220.dp.toPx(), height = 200.dp.toPx()),
                    cornerRadius = CornerRadius(80.0f),
                    style = Fill,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                    blendMode = DefaultBlendMode
                )





                if (fimp) {
                    //  Thread.sleep(1)
                    time++
                    if (time > 1) {


                        xvc = if (xvc < 1f) xvc + 0.1f else 1f

                        xvcP = if (xvcP > 10f) xvcP - 1f else 10f
                        yvcP = if (yvcP < 20f) yvcP + 1f else 20f


                        drawIntoCanvas {
                            it.save()
                            it.translate((xvcP.dp.toPx()), (yvcP.dp.toPx()))
                            it.scale(xvc.toFloat(), xvc.toFloat())

                            it.nativeCanvas.drawPaint(
                                paint
                            )

                            it.restore()
                        }
                    }
                    if (time > 11) {


                        xvc2 = if (xvc2 < 1.5f) xvc2 + 0.2f else 1.5f


                        xvcP2 = if (xvcP2 > 75f) xvcP2 - 1f else 75f
                        yvcP2 = if (yvcP2 > 0f) yvcP2 - 1f else 0f



                        drawIntoCanvas {
                            it.save()
                            it.translate((xvcP2.dp.toPx()), (yvcP2.dp.toPx()))

                            it.scale(xvc2.toFloat(), xvc2.toFloat())
                            it.nativeCanvas.drawPaint(
                            paint2

                            )

                            it.restore()


                        }
                    }
                    if (time > 21) {


                        xvc3 = if (xvc3 < 1f) xvc3 + 0.1f else 1f


                        xvcP3 = if (xvcP3 > 160f) xvcP3 - 1f else 160f
                        yvcP3 = if (yvcP3 < 20f) yvcP3 + 1f else 20f



                        drawIntoCanvas {

                            it.save()
                            it.translate((xvcP3.dp.toPx()), (yvcP3.dp.toPx()))

                            it.scale(xvc3.toFloat(), xvc3.toFloat())

                            it.nativeCanvas.drawPaint(
                                paint3
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
                if (msg != "") {
                    drawText(
                        textLayoutResult = msgem,
                        topLeft = Offset(30.dp.toPx(), 85.dp.toPx())
                    )
                }

                drawText(
                    textLayoutResult = result,
                    topLeft = Offset(70.dp.toPx(), 165.dp.toPx())

                )
            }



            Spacer(modifier = modifier.padding(5.dp))

            Botao(onClick = onclick, modifier = Modifier.offset {
                IntOffset(
                    x = 0,
                    y = 0
                )
            }, "->")

        }

    }


}



