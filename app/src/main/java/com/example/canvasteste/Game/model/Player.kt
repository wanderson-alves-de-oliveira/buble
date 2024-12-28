package com.example.canvasteste.Game.model

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3

data class Player(
    val y: Dp,
    val h: Dp,
    val w: Dp,
    val speed: Float,
    val rotacao: MutableList<Offset3> =  mutableListOf(Offset3(0f,0f,true,-1)),
    val girar: Boolean=false,
    val size: Size = Size(70f, 36.65f),
    val posRebote:MutableList<Offset3> = mutableListOf(Offset3(0f,0f,true,-1),Offset3(0f,0f,true,-1),Offset3(0f,0f,true,-1)),
    val posprev: List<Int> = mutableListOf(0),

    val cor:Color = Color.Transparent
) {

    companion object {
        const val acceleration = 0.001f
          val h = 36.dp
          val w = 70.dp
          val rotacao = 0f
    }
}