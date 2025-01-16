package com.example.canvasteste.game.model

import com.example.canvasteste.R

data class Cores(
    val cores:MutableList<Int>,
    val listCores: MutableList<Int> = mutableListOf(R.drawable.red, R.drawable.blue, R.drawable.yaelow, R.drawable.pink,R.drawable.green)
)
