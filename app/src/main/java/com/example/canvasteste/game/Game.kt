package com.example.canvasteste.game

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.canvasteste.game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.game.di.engeni.ferramentaUx.Formas
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela
import com.example.canvasteste.game.logic.AAbilite
import com.example.canvasteste.game.logic.CCores
import com.example.canvasteste.game.logic.CCoresSeparacao
import com.example.canvasteste.game.logic.PlayLogic
import com.example.canvasteste.game.model.Viewport
import com.example.canvasteste.game.ui.Background
import com.example.canvasteste.game.ui.Player

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
        val coresSeparacao = CCoresSeparacao()

         val fase:String= param ?: ""
        rememberDI(viewPort)

//
//        LaunchedEffect(key1 = Unit) {
//            di.timeManager.deltaTime.collect { it ->
//
//                    playerLogic.OnMusica(true)
//
//            }
//        }



val formas = Formas()

        Box(
            modifier = Modifier.fillMaxSize()
                        )
        {




            var k = fase.toInt()-1
            val quadrado = formas.pegarQuadrado()
            val circulos = formas.pegarCirculos(k)
            val flores = formas.pegarFlores(k)
            val maze = formas.pegarMaze(k)
            val flechas = formas.pegarFlecha(k)
            val maim = formas.pegarMain(k)


            val listt = mutableListOf(quadrado,circulos,maze,flores,flechas,maim)


            if(k>listt.size-1) k=listt.size-1



        abilite.onUpdate(listt[k][0])
        cores.onUpdate(listt[k][1])


val i = if(k%3==0) {
    0
} else if(k%2==0){
    1
        }else{
            2
        }


            Background(tela, i)
            Player( Modifier, playerLogic,abilite,cores,coresSeparacao,viewPort,tela,navController,fase)


        }
    }


}




