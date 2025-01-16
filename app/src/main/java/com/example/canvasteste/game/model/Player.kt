package com.example.canvasteste.game.model
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.canvasteste.game.di.engeni.ferramentaUx.Offset3
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
)