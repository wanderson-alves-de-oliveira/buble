package com.example.canvasteste.Game.ui
import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
internal fun Botao(onClick : () -> Unit,modifier: Modifier,text:String){
    ElevatedButton (
        onClick = onClick,
        modifier = Modifier
            .height(56.dp)
            .width(100.dp),
        colors = ButtonColors(containerColor =  Color(0xFFDE644D),
            contentColor = White,
            disabledContainerColor = Green,
            disabledContentColor = Blue),
        contentPadding = PaddingValues(horizontal = 10.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        border = BorderStroke(Resources.getSystem().displayMetrics.density.dp, color = Color(
            0xFFE88E81
        )
        )

        ){
        Text(text = text,
            modifier = Modifier,
            color = Color.White,
            fontSize = 30.sp
        )

    }
}

