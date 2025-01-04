package com.example.canvasteste.Game.ui
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvasteste.R

@Composable
internal fun Botao(context: Context,onClick : () -> Unit,modifier: Modifier,text:String = "",imageBt:Int= R.drawable.seta){
//    ElevatedButton (
//        onClick = onClick,
//        modifier = Modifier
//            .height(56.dp)
//            .width(100.dp),
//        colors = ButtonColors(containerColor =  Color(0xFFDE644D),
//            contentColor = White,
//            disabledContainerColor = Green,
//            disabledContentColor = Blue),
//        contentPadding = PaddingValues(horizontal = 10.dp),
//        elevation = ButtonDefaults.buttonElevation(0.dp),
//        border = BorderStroke(Resources.getSystem().displayMetrics.density.dp, color = Color(
//            0xFFE88E81
//        )
//        )
//
//        ){
//
//
//    }
    val textMeasurer = rememberTextMeasurer()
    val result = remember {
        textMeasurer.measure(
            text = text,
            style = TextStyle(fontSize = 22.sp, color = Color(0xFFFAEFEF)),
            overflow = TextOverflow.Ellipsis
        )
    }
    val res = context.resources

    var b1 = BitmapFactory.decodeResource(res, imageBt)

    val paint = Paint().asFrameworkPaint().apply {
        shader = BitmapShader(
            b1.resizeTo(25.dp.toPx().toInt()),
            android.graphics.Shader.TileMode.DECAL,
            android.graphics.Shader.TileMode.DECAL
        )

    }



FilledIconButton ( onClick = onClick,modifier = Modifier.size(120.dp, 50.dp), colors = IconButtonColors(
    Transparent, Transparent, Transparent, Transparent)) {

    Column(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = 0,
                    y = 0
                )
            },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Canvas(modifier = Modifier.size(110.dp, 45.dp)) {
            drawRoundRect(
                color = Color(0xFF75ACDA),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 90.dp.toPx(), height = 40.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )


            drawRoundRect(
                color = Color(Color(16, 180, 39, 155).toArgb()),
                topLeft = Offset(0.dp.toPx(), 0.dp.toPx()),
                size = Size(width = 90.dp.toPx(), height = 45.dp.toPx()),
                cornerRadius = CornerRadius(45.0f),
                style = Fill,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix()),
                blendMode = DefaultBlendMode
            )

if(text!="") {
    drawText(
        textLayoutResult = result,
        topLeft = Offset(30.dp.toPx(), 5.dp.toPx())

    )
}else{


    drawIntoCanvas {
        it.save()
        it.translate((25.dp.toPx()), 10.dp.toPx())

        it.nativeCanvas.drawPaint(
            paint
        )

        it.restore()

    }



}

        }
    }


}

}

