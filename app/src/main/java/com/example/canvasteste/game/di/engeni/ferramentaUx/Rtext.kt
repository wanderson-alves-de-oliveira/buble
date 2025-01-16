package com.example.canvasteste.game.di.engeni.ferramentaUx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

class Rtext() {


     @Composable
     fun texto(texto:String): TextLayoutResult {
        val textMeasurer = rememberTextMeasurer()

        val result = remember {
            textMeasurer.measure(
                text = texto,
                style = TextStyle(fontSize = 22.sp, color = Color(0xFFFAEFEF)),
                overflow = TextOverflow.Ellipsis
            )
        }
        return result
    }
}




