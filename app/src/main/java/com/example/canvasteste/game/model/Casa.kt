package com.example.canvasteste.game.model

import android.graphics.Bitmap
import androidx.compose.ui.geometry.Offset

data class Casa(val bitmap: Bitmap,
                val offset: Offset,
                val escala: Float,
                val numero: Int
)
