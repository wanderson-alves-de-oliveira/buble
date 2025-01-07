package com.example.canvasteste.Game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.R


internal fun CardCanvas(
    parametros: MutableList<Any>,
    canvas: androidx.compose.ui.graphics.Canvas,
    imageBt:Int= R.drawable.seta,
    onclick: () -> Unit,
    onclickX: () -> Unit
) {

    val mfloat = parametros[0] as Float
    val canvas:Canvas = Canvas()
    val context: Context = parametros[2] as Context
     var tela: Tela = Tela(context )

    var pintar:Paint = Paint()

    val res = context.resources

    val msg = parametros[6]



        val yfinal = (tela.getTamanhoTela().y / 2) - 100*mfloat.toInt()
        val fy = 1200*mfloat
        var yfinalP: Float = mfloat*0
        var fimp: Boolean = false


        if (yfinalP > yfinal) {
            var dist = yfinalP - yfinal
            yfinalP -= (dist / 10)*mfloat

            if (dist < 10) {
                fimp = true
            }
        }


        var time: Int =0
        var xvc: Float =0f
        var xvc2: Float =0f
        var xvc3: Float =0f

        var xvcP: Float =0f
        var yvcP: Float=0f

        var xvcP2: Float =0f
        var yvcP2: Float =0f

        var xvcP3: Float =0f
        var yvcP3: Float =0f
    var w=  parametros[7] as Float
    var h=  parametros[8] as Float




    pintar.color= Color.argb(255,255,255,255)

    canvas.drawRoundRect(
        /* left = */ 0f,
        /* top = */0f,
        /* right = */ 250f,
        /* bottom = */ (h * 1).toFloat(), /* rx = */ 70f, /* ry = */ 70f,
        /* paint = */ pintar
    )




}






