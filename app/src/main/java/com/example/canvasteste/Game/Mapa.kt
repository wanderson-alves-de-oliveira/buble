package com.example.canvasteste.Game

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.logic.AAbilite
import com.example.canvasteste.Game.logic.CCores
import com.example.canvasteste.Game.logic.CCoresSeparacao
import com.example.canvasteste.Game.logic.PlayLogic
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.Background
import com.example.canvasteste.Game.ui.Card
import com.example.canvasteste.R;

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Mapa(navController: androidx.navigation.NavController,context: Context,modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        val viewPort = remember {
            Viewport(maxWidth, maxHeight)
        }
        val tela = Tela(context)
        val di = rememberDI(viewPort)
        Box(
            modifier = Modifier.fillMaxSize()
                        )
        {
           Background(di.timeManager)
            Column(
                modifier = modifier
                    .size(250.dp, 310.dp)              ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val res = tela.context.resources
                var b = BitmapFactory.decodeResource(res, R.drawable.blue)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Card(b,tela.context, modifier = modifier,"->","Nivel 1",true,"TELA DO MAPA", onclick = {
                        navController.navigate("game")
                    })
                }
            }
        }
    }
}




