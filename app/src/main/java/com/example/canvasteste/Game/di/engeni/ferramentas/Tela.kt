package com.example.canvasteste.Game.di.engeni.ferramentas

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.example.canvasteste.Game.ui.toPx


class Tela(context: Context) {

      val context: Context = context
     var densidade:Float = 0f

    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()

    @RequiresApi(Build.VERSION_CODES.R)
    fun getTamanhoTela(): Offset {


        windowManager.getDefaultDisplay().getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels
        val h = displayMetrics.heightPixels
        densidade = displayMetrics.density


        return Offset(w.toFloat(), h.toFloat())
    }

    companion object {

        @Composable
        fun Dp.toF(context: Context): Float {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.getDefaultDisplay().getMetrics(displayMetrics)

            val scale = displayMetrics.density
            return (this / scale).toPx()
        }




    }
}