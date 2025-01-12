package com.example.canvasteste.Game

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.canvasteste.Game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.Game.di.engeni.ferramentas.Formas
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.logic.AAbilite
import com.example.canvasteste.Game.logic.CCores
import com.example.canvasteste.Game.logic.CCoresSeparacao
import com.example.canvasteste.Game.logic.PlayLogic
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.Background
import com.example.canvasteste.Game.ui.Player

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Game(navController: NavController,context: Context,param: String?="" ) {

    BoxWithConstraints(modifier = Modifier) {



        val viewPort = remember {
            Viewport(maxWidth, maxHeight)
        }
        val tela = Tela(context)
        val playerLogic = PlayLogic(viewPort,context)
        val abilite = AAbilite(context)
        val cores = CCores(context)
        val coresSeparacao = CCoresSeparacao(context)

         val fase:String= if(param!=null) param else ""
        val di = rememberDI(viewPort)

//
//        LaunchedEffect(key1 = Unit) {
//            di.timeManager.deltaTime.collect { it ->
//
//                    playerLogic.OnMusica(true)
//
//            }
//        }



val formas:Formas = Formas()

        Box(
            modifier = Modifier.fillMaxSize()
                        )
        {




            var k = fase.toInt()-1
            var quadrado = formas.pegarQuadrado(k)
            var circulos = formas.pegarCirculos(k)
            var flores = formas.pegarFlores(k)
            var maze = formas.pegarMaze(k)
            var flechas = formas.pegarFlecha(k)
            var listt = mutableListOf(quadrado,circulos,maze,flores,flechas)

            if(k>listt.size-1) k=listt.size-1

        abilite.onUpdate(listt[k][0])
        cores.onUpdate(listt[k][1])


var i = if(k%3==0) {
    0
} else if(k%2==0){
    1
        }else{
            2
        }


            Background(di.timeManager,tela,i)
            Player( Modifier, playerLogic,abilite,cores,coresSeparacao,viewPort,tela,navController,fase)


        }
    }


}




