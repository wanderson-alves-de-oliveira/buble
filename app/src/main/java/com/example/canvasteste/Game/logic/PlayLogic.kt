package com.example.canvasteste.Game.logic
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3
import com.example.canvasteste.Game.model.Player
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.Game.ui.toPx
import com.example.canvasteste.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

class PlayLogic(viewport: Viewport,context: Context) {
    private var posLI: MutableList<Int> = mutableListOf()
    private var posLIR: MutableList<Int> = mutableListOf()
    private val default: Player = Player(viewport.height, 36.dp, 70.dp, 0f)
    private val _playPosition = MutableStateFlow<Player>(default)
    private val context: Context = context

    private var efeitoSonoro: MediaPlayer = MediaPlayer.create(this.context, R.raw.glassmini)
    private var efeitoSonoro2: MediaPlayer = MediaPlayer.create(this.context, R.raw.glass)
    private var dim: MediaPlayer = MediaPlayer.create(this.context, R.raw.dim)
    private var zom: MediaPlayer = MediaPlayer.create(this.context, R.raw.zom)
    val player: StateFlow<Player> = _playPosition
    private var songs: Int = 0

    fun updatePrev(posL: List<Int>) {
        _playPosition.update { player ->
            var new = posL
            player.copy(posprev = new)
        }
    }

    @Composable
    fun updateLimparnit(
        listaAtual: MutableList<Offset3>,
        posL: List<Int>,
        init: Int
    ): MutableList<Int> {
        posLI.clear()
        updateLimpar(listaAtual, posL, init)
        return posLI
    }

    @Composable
    fun updateLimpar(listaAtual: MutableList<Offset3>, posL: List<Int>, init: Int) {

        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {
            if (posLI.contains(init)) return
            posLI.add(init)
            var lista = listaAtual
            var listaI = posL.filter { it -> !posLI.contains(it) && it != 0 }
            var posLD: MutableList<Int> = mutableListOf()

            for (i in 0..listaI.size - 1) {
                var m1 = (lista[listaI[i]].x - lista[init].x).pow(2)
                var m2 = (lista[listaI[i]].y - lista[init].y).pow(2)
                var d: Float = sqrt((m1 + m2))
                if (d < 50.dp.toPx()) {
                    posLD.add(listaI[i])
                }
            }

            for (i in 0..posLD.size - 1) {
                updateLimpar(listaAtual, posL, posLD[i])
            }
        }

    }

    @Composable
    fun updateLimparRamosinit(
        listaAtual: MutableList<Offset3>,
        posL: List<Int>,
        init: Int
    ): MutableList<Int> {
        posLIR.clear()

        updateLimparRamos(listaAtual, posL, init)
        return posLIR
    }

    @Composable
    fun updateLimparRamos(listaAtual: MutableList<Offset3>, posL: List<Int>, init: Int) {


        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {

            if (posLIR.contains(init)) {


                return
            }
            posLIR.add(init)
            var lista = listaAtual
            var listaI = posL.filter { it -> !posLIR.contains(it) && it != 0 }
            var posLD: MutableList<Int> = mutableListOf()

            for (i in 0..listaI.size - 1) {
                var m1 = (lista[listaI[i]].x - lista[init].x).pow(2)
                var m2 = (lista[listaI[i]].y - lista[init].y).pow(2)
                var d: Float = sqrt((m1 + m2))
                if (d < 60.dp.toPx()) {
                    posLD.add(listaI[i])
                }
            }
            if (init < 10 && posLD.size == 0) updateLimparRamos(listaAtual, posL, init + 1)
            for (i in 0..posLD.size - 1) {
                updateLimparRamos(listaAtual, posL, posLD[i])
            }
        }

    }

    @Composable
    fun OnTocarEfeito(intt: Int) {
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {

            if (songs < 3) {
                songs++
                if (intt > 7) {
                    efeitoSonoro2.setVolume(0.2f, 0.2f)
                    efeitoSonoro2.seekTo(0)
                    efeitoSonoro2.start()

                } else {
                    efeitoSonoro.setVolume(0.2f, 0.2f)
                    efeitoSonoro.seekTo(0)
                    efeitoSonoro.start()

                }
            } else {
                songs = 0
            }

        }
    }

    @Composable
    fun OnDim() {
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {


            dim.setVolume(0.2f, 0.2f)
            dim.seekTo(0)
            dim.start()


        }
    }


    fun OnZom() {
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {


            zom.setVolume(0.2f, 0.2f)
            zom.seekTo(0)
            zom.start()


        }
    }
        fun verificarCorPresente(lista: MutableList<Int>): MutableList<Int> {
            val listCores: MutableList<Int> = mutableListOf(
                R.drawable.red,
                R.drawable.blue,
                R.drawable.yaelow,
                R.drawable.pink,
                R.drawable.green
            )
            var listCoresR: MutableList<Int> = listCores

            val coroutineScope = CoroutineScope(Dispatchers.Default)

            coroutineScope.run {

                listCoresR = listCores.filter { it -> lista.contains(it) } as MutableList<Int>


            }
            return listCoresR
        }



}
