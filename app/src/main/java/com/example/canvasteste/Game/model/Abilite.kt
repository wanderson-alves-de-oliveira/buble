package com.example.canvasteste.Game.model

import androidx.compose.ui.unit.dp

data class Abilite(
    val pos:MutableList<Int>,
    val posRamo:MutableList<Int> = mutableListOf( 0),
)
