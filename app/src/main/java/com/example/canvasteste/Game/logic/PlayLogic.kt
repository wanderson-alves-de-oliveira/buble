package com.example.canvasteste.Game.logic


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import com.example.canvasteste.Game.model.Player
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.toPx
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

class PlayLogic(viewport: Viewport ) {
    private var posLI: MutableList<Int> = mutableListOf()
    private val default: Player = Player(viewport.height, 36.dp, 70.dp, 0f )
    private val _playPosition = MutableStateFlow<Player>(default)
    val player: StateFlow<Player> = _playPosition

    fun onUpdate(deltaTime: Float) {
        _playPosition.update { player ->
            var newY =
                player.y + (player.speed * deltaTime + 0.5 * Player.acceleration * deltaTime * deltaTime).dp
            var newSpeed = player.speed + Player.acceleration * deltaTime

            if (newY > 2000.dp) {
                newY = 0.dp
                newSpeed = 0f
            }

            player.copy(y = newY, speed = newSpeed)
        }
    }



    fun girarando(gira: Boolean) {
        _playPosition.update { player ->
            var newY = gira
            player.copy(girar = newY)
        }
    }


    fun updatePrev(posL: List<Int>) {
        _playPosition.update { player ->
            var new = posL
            player.copy(posprev = new)
        }
    }


    fun updateLimparnit(
        listaAtual: MutableList<Offset3>,
        posL: List<Int>,
        init: Int
    ): MutableList<Int> {
        posLI.clear()
        updateLimpar(listaAtual, posL, init)

        return posLI
    }

    fun updateLimpar(listaAtual: MutableList<Offset3>, posL: List<Int>, init: Int) {

        if (posLI.contains(init)) return


        posLI.add(init)

        var lista = listaAtual
        var listaI = posL.filter { it -> !posLI.contains(it) && it != 0 }
        var posLD: MutableList<Int> = mutableListOf()

        try {


            for (i in 0..listaI.size - 1) {

                //sqrt( pow( x2 - x1, 2 ) + pow( y2 - y1, 2 ) )
                var m1 = (lista[listaI[i]].x - lista[init].x).pow(2)
                var m2 = (lista[listaI[i]].y - lista[init].y).pow(2)
                var d: Float = sqrt((m1 + m2))
                if (d < 111) {
                    posLD.add(listaI[i])
                }
            }
        } catch (e: Exception) {

            Log.e("ERRO", e.message.toString())
        }
        for (i in 0..posLD.size - 1) {
            updateLimpar(listaAtual, posL, posLD[i])
        }


    }

}