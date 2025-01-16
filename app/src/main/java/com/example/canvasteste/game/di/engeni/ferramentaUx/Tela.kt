package com.example.canvasteste.game.di.engeni.ferramentaUx

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.ui.geometry.Offset


@Suppress("DEPRECATION")
class Tela(val context: Context) {

    var densidade:Float = 0f

    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()

    fun getTamanhoTela(): Offset {


        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels
        val h = displayMetrics.heightPixels
        densidade = displayMetrics.density


        return Offset(w.toFloat(), h.toFloat())
    }

    companion object {


    }
}