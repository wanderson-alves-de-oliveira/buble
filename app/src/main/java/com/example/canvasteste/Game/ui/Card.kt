package com.example.canvasteste.Game.ui

import android.R
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.R.drawable
import com.example.canvasteste.R.drawable.*


@Composable
internal fun Card(
    context: Context,
    modifier: Modifier,
    textButton: String,
    textCard: String,
    fim: Boolean
) {
    val context: Context = context


    Box {


        val res = context.resources
        var b = BitmapFactory.decodeResource(res, estrela)
        var time: Int by remember { mutableStateOf(0) }
        var xvc: Float by remember { mutableStateOf(0f) }
        var xvc2: Float by remember { mutableStateOf(0f) }
        var xvc3: Float by remember { mutableStateOf(0f) }

        var xvcP: Float by remember { mutableStateOf(20f) }
        var yvcP: Float by remember { mutableStateOf(20f) }

        var xvcP2: Float by remember { mutableStateOf(70f) }
        var yvcP2: Float by remember { mutableStateOf(20f) }

        var xvcP3: Float by remember { mutableStateOf(130f) }
        var yvcP3: Float by remember { mutableStateOf(20f) }


        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                b.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.CLAMP,
                android.graphics.Shader.TileMode.CLAMP
            )

        }


        val paint2 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                b.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.CLAMP,
                android.graphics.Shader.TileMode.CLAMP
            )

        }

        val paint3 = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                b.resizeTo(45.dp.toPx().toInt()),
                android.graphics.Shader.TileMode.CLAMP,
                android.graphics.Shader.TileMode.CLAMP
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

        Column(
            modifier = Modifier.size(200.dp, 250.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Canvas(modifier = Modifier.size(180.dp, 170.dp)) {
                drawRoundRect(
                    color = Color(0xFF7898DC),
                    size = Size(width = 180.dp.toPx(), height = 170.dp.toPx()),
                    cornerRadius = CornerRadius(80.0f),
                    style = Fill,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                    blendMode = DefaultBlendMode
                )



                drawRoundRect(
                    color = Color(0xFF6893EA),
                    topLeft = Offset(10.dp.toPx(), 7.dp.toPx()),
                    size = Size(width = 160.dp.toPx(), height = 150.dp.toPx()),
                    cornerRadius = CornerRadius(80.0f),
                    style = Fill,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                    blendMode = DefaultBlendMode
                )
                drawText(
                    textLayoutResult = result,
                    topLeft = Offset(50.dp.toPx(), 125.dp.toPx())
                )

                if (fim) {
                  //  Thread.sleep(1)
                    time++
                    if (time > 1) {


                        xvc = if(xvc<1f)  xvc+0.1f else 1f

                        xvcP = if(xvcP>10f)  xvcP-1f else 10f
                        yvcP = if(yvcP>0f)  yvcP-1f else 0f


                        drawIntoCanvas {
                            it.save()
                            it.translate((xvcP.dp.toPx()), (yvcP.dp.toPx()))
                            it.scale(xvc.toFloat(),xvc.toFloat())

                            it.nativeCanvas.drawPaint(
                                paint
                            )
                            it.restore()


                        }
                    }
                    if (time > 11) {


                        xvc2 = if(xvc2<1f)  xvc2+0.1f else 1f


                        xvcP2 = if(xvcP2>60f)  xvcP2-1f else 60f
                        yvcP2 = if(yvcP2>0f)  yvcP2-1f else 0f



                        drawIntoCanvas {
                            it.save()
                            it.translate((xvcP2.dp.toPx()), (yvcP2.dp.toPx()))

                            it.scale(xvc2.toFloat(),xvc2.toFloat())

                            it.nativeCanvas.drawPaint(
                                paint2
                            )

                            it.restore()


                         }
                    }
                    if (time > 21) {


                        xvc3 = if(xvc3<1f)  xvc3+0.1f else 1f


                        xvcP3 = if(xvcP3>120f)  xvcP3-1f else 120f
                        yvcP3 = if(yvcP3>0f)  yvcP3-1f else 0f



                        drawIntoCanvas {

                            it.save()
                            it.translate((xvcP3.dp.toPx()), (yvcP3.dp.toPx()))

                            it.scale(xvc3.toFloat(),xvc3.toFloat())

                            it.nativeCanvas.drawPaint(
                                paint3
                            )

                            it.restore()
                         }
                    }

                }


            }



            Spacer(modifier = modifier.padding(5.dp))

            Botao(modifier = Modifier, "->")

        }

    }


}



