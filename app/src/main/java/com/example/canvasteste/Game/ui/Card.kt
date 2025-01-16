package com.example.canvasteste.Game.ui

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    msg: String = "",
    imageBt: Int = R.drawable.seta,
    onclick: () -> Unit,
    onclickX: () -> Unit
) {
    val context: Context = context
    var tela: Tela = Tela(context)
    var escala: Float by remember { mutableStateOf(0f) }


    val textMeasurer = rememberTextMeasurer()
    val res = context.resources
    Box {


        Image(
            painterResource(id = R.drawable.painel),
            contentDescription = null,
            modifier = modifier
                .offset {
                    IntOffset(
                        x = 0,
                        y = 0
                    )
                }
                .size(300.dp)
                .scale(1f)
        )





//        val paint4 = Paint().asFrameworkPaint().apply {
//            shader = BitmapShader(
//                img.resizeTo(45.dp.toPx().toInt()),
//                android.graphics.Shader.TileMode.DECAL,
//                android.graphics.Shader.TileMode.DECAL
//            )
//
//        }
//
//        val yfinal = ((tela.getTamanhoTela().y / 2) - 100.dp.toPx()).toFloat().toInt()
//        val fy = 1200.dp.toPx()
//        var yfinalP: Float by remember { mutableStateOf(fy) }
//        var fimp: Boolean by remember { mutableStateOf(false) }
//
//
//        if (yfinalP > yfinal) {
//            var dist = yfinalP - yfinal
//            yfinalP -= (dist / 8).dp.toPx()
//
//            if (dist < 10) {
//                yfinalP = yfinal.toFloat()
//                fimp = true
//            }
//        }
//
//
        val msgem = remember {
            textMeasurer.measure(
                text = msg,
                style = TextStyle(fontSize = 16.sp, color = Color(0xFFFAEFEF)),
                overflow = TextOverflow.Ellipsis
            )
        }
        val fase = remember {
            textMeasurer.measure(
                text = "Nível $textCard" ,
                style = TextStyle(fontSize = 18.sp, color = Color(0xFFFAEFEF)),
                overflow = TextOverflow.Ellipsis
            )
        }
        Column {
            Canvas(modifier = Modifier.size(230.dp, 210.dp)) {

                val pincel = android.graphics.Paint()
                pincel.color = android.graphics.Color.argb(
                    255,
                    255,
                    255,
                    255
                )



                drawText(
                    textLayoutResult = msgem,
                    topLeft = Offset(70.dp.toPx(), 125.dp.toPx())
                )



                drawText(
                    textLayoutResult = fase,
                    topLeft = Offset(115.dp.toPx(), 205.dp.toPx())
                )



            }

        }
//
//        Column(
//            modifier = Modifier
//                .offset {
//                    IntOffset(
//                        x = ((tela.getTamanhoTela().x / 2) - 120.dp.toPx()).toInt(),
//                        y = yfinalP.toInt()
//                    )
//                },
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//
//            Canvas(modifier = Modifier.size(230.dp, 210.dp)) {
//
//
//                drawRoundRect(
//                    color = Color(0xFF486CBB),
//                    topLeft = Offset(-10.dp.toPx(), 5.dp.toPx()),
//                    size = Size(width = 230.dp.toPx(), height = 210.dp.toPx()),
//                    cornerRadius = CornerRadius(80.0f),
//                    style = Fill,
//                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                    blendMode = DefaultBlendMode
//                )
//
//
//
//                drawRoundRect(
//                    color = Color(0xFF346ADA),
//                    topLeft = Offset(-3.dp.toPx(), 5.dp.toPx()),
//                    size = Size(width = 220.dp.toPx(), height = 200.dp.toPx()),
//                    cornerRadius = CornerRadius(80.0f),
//                    style = Fill,
//                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                    blendMode = DefaultBlendMode
//                )
//
//
//
//                    var b1 = BitmapFactory.decodeResource(res, R.drawable.green)
//                    var b2 = BitmapFactory.decodeResource(res, R.drawable.red)
//                    var b3 = BitmapFactory.decodeResource(res, R.drawable.blue)
//
//
//                    val paint = Paint().asFrameworkPaint().apply {
//                        shader = BitmapShader(
//                            b1.resizeTo(45.dp.toPx().toInt()),
//                            android.graphics.Shader.TileMode.DECAL,
//                            android.graphics.Shader.TileMode.DECAL
//                        )
//
//                    }
//
//
//                    val paint2 = Paint().asFrameworkPaint().apply {
//                        shader = BitmapShader(
//                            b2.resizeTo(45.dp.toPx().toInt()),
//                            android.graphics.Shader.TileMode.DECAL,
//                            android.graphics.Shader.TileMode.DECAL
//                        )
//
//                    }
//
//                    val paint3 = Paint().asFrameworkPaint().apply {
//                        shader = BitmapShader(
//                            b3.resizeTo(45.dp.toPx().toInt()),
//                            android.graphics.Shader.TileMode.DECAL,
//                            android.graphics.Shader.TileMode.DECAL
//                        )
//
//                    }
//
//
//                    drawIntoCanvas {
//                        it.save()
//                        it.translate((10.dp.toPx()), (20.dp.toPx()))
//
//                        it.nativeCanvas.drawPaint(
//                            paint
//                        )
//
//                        it.restore()
//                    }
//
//
//
//                    drawIntoCanvas {
//                        it.save()
//                        it.translate((75.dp.toPx()), (0.dp.toPx()))
//                        it.scale(1.5f, 1.5f)
//                        it.nativeCanvas.drawPaint(
//                            paint2
//
//                        )
//
//                        it.restore()
//
//
//                    }
//
//
//                    drawIntoCanvas {
//
//                        it.save()
//                        it.translate((160.dp.toPx()), (20.dp.toPx()))
//
//
//                        it.nativeCanvas.drawPaint(
//                            paint3
//                        )
//
//                        it.restore()
//                    }
//                    drawRoundRect(
//                        color = Color(0xFF75ACDA),
//                        topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
//                        size = Size(width = 205.dp.toPx(), height = 80.dp.toPx()),
//                        cornerRadius = CornerRadius(45.0f),
//                        style = Fill,
//                        colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                        blendMode = DefaultBlendMode
//                    )
//
//
//                    drawRoundRect(
//                        color = Color(Color(229, 146, 225, 150).toArgb()),
//                        topLeft = Offset(5.dp.toPx(), 75.dp.toPx()),
//                        size = Size(width = 205.dp.toPx(), height = 75.dp.toPx()),
//                        cornerRadius = CornerRadius(45.0f),
//                        style = Fill,
//                        colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                        blendMode = DefaultBlendMode
//                    )
//
//                    drawIntoCanvas {
//                        it.save()
//                        it.translate((10.dp.toPx()), 125.dp.toPx())
//
//                        it.nativeCanvas.drawPaint(
//                            paint4
//                        )
//
//                        it.restore()
//
//                    }
//                    drawText(
//                        textLayoutResult = msgem,
//                        topLeft = Offset(40.dp.toPx(), 105.dp.toPx())
//                    )
//
//                drawRoundRect(
//                    color = Color(0xFF75ACDA),
//                    topLeft = Offset(50.dp.toPx(), 165.dp.toPx()),
//                    size = Size(width = 110.dp.toPx(), height = 40.dp.toPx()),
//                    cornerRadius = CornerRadius(45.0f),
//                    style = Fill,
//                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                    blendMode = DefaultBlendMode
//                )
//
//
//                drawRoundRect(
//                    color = Color(Color(232, 2, 73, 150).toArgb()),
//                    topLeft = Offset(50.dp.toPx(), 160.dp.toPx()),
//                    size = Size(width = 110.dp.toPx(), height = 45.dp.toPx()),
//                    cornerRadius = CornerRadius(45.0f),
//                    style = Fill,
//                    colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
//                    blendMode = DefaultBlendMode
//                )
//
//
//                val pincel = android.graphics.Paint()
//                pincel.color = android.graphics.Color.argb(
//                    255,
//                    255,
//                    255,
//                    255
//                )
//                pincel.textSize = 20.sp.toPx()
//
//                drawIntoCanvas {
//                    it.save()
//
//                    it.nativeCanvas.drawText("Nível $textCard", 70.dp.toPx(), 190.dp.toPx(), pincel)
//
//                    it.restore()
//
//                }
//
//
//            }
//
//
//            Box(modifier = modifier) {
//                Spacer(modifier = modifier.padding(5.dp))
//
//                Botao(
//                    context = context,
//                    onClick = onclick,
//                    modifier = Modifier,
//                    text = textButton,
//                    imageBt = imageBt
//                )
//
//            }
//
//                Box(modifier = modifier.offset {
//                    IntOffset(
//                        x = 120.dp.toPx().toInt(),
//                        y = -310.dp.toPx().toInt()
//                    )
//                }) {
//                    Spacer(modifier = modifier.padding(15.dp))
//
//                    Botao(
//                        context = context,
//                        onClick = onclickX,
//                        modifier = Modifier.size(45.dp, 45.dp),
//                        text = "X",
//                        imageBt = imageBt
//                    )
//
//                }
//
//        }


    }


}



