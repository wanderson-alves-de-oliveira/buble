package com.example.canvasteste.game.model
import com.example.canvasteste.game.di.engeni.ferramentaUx.Offset3
data class Abilite(
    val pos:MutableList<Int>,
    val posRamo:MutableList<Int> = mutableListOf( 0),
    val posMove:MutableList<Offset3>  ,
    val posMoveReset:MutableList<Offset3> = mutableListOf(Offset3(0f, 0f, false, 0)) ,
    val ultimaLinha: Int = 0,
    val posRef:Offset3 = Offset3(0f, 0f, false, 0)  ,
    )
