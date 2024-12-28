package com.example.canvasteste.Game.di.engeni.ferramentas

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.ui.geometry.Offset


class Tela(context: Context) {

    private val context: Context = context
     var densidade:Float = 0f


    @RequiresApi(Build.VERSION_CODES.R)
    fun getTamanhoTela(): Offset {

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()

        windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels
        val h = displayMetrics.heightPixels
        densidade = displayMetrics.density


        return Offset(w.toFloat(), h.toFloat())
    }

}