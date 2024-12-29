package com.example.canvasteste.Game.model

import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3

data class Abilite(
    val pos:MutableList<Int>,
    val posRamo:MutableList<Int> = mutableListOf( 0),
    val posMove:MutableList<Offset3> = mutableListOf( Offset3(0f,0f,true,0)),

    )
