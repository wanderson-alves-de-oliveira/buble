package com.example.canvasteste.game.logic
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.canvasteste.game.di.engeni.ferramentaUx.Offset3
import com.example.canvasteste.game.model.Player
import com.example.canvasteste.game.model.Viewport
import com.example.canvasteste.game.ui.toPx
import com.example.canvasteste.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow
import kotlin.math.sqrt

class PlayLogic(viewport: Viewport, private val context: Context) {
    private var posLI: MutableList<Int> = mutableListOf()
    private var posLIR: MutableList<Int> = mutableListOf()
    private val default: Player = Player(viewport.height, 36.dp, 70.dp, 0f)
    private val _playPosition = MutableStateFlow(default)

    private var efeitoSonoro: MediaPlayer = MediaPlayer.create(this.context, R.raw.buble)
    private var efeitoSonoro2: MediaPlayer = MediaPlayer.create(this.context, R.raw.buble)
    private var dim: MediaPlayer = MediaPlayer.create(this.context, R.raw.dim)
    private var zom: MediaPlayer = MediaPlayer.create(this.context, R.raw.zom)
    private var fim: MediaPlayer = MediaPlayer.create(this.context, R.raw.finalyy)
    var top: MediaPlayer = MediaPlayer.create(this.context, R.raw.topw)
    var topb: MediaPlayer = MediaPlayer.create(this.context, R.raw.topb)
    var son: Int = 1
    var reiniciarSom:Boolean = false
    val player: StateFlow<Player> = _playPosition
    private var songs: Int = 0

    fun updatePrev(posL: List<Int>) {
        _playPosition.update { player ->

            player.copy(posprev = posL)
        }
    }

    @Composable
    fun updateLimparnit(
        listaAtual: MutableList<Offset3>,
        posL: List<Int>,
        init: Int
    ): MutableList<Int> {
        posLI.clear()
        UpdateLimpar(listaAtual, posL, init)
        return posLI
    }

    @Composable
    fun UpdateLimpar(listaAtual: MutableList<Offset3>, posL: List<Int>, init: Int) {

        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {
            if (posLI.contains(init)) return
            posLI.add(init)
            val listaI = posL.filter { !posLI.contains(it) && it != 0 }
            val posLD: MutableList<Int> = mutableListOf()

            for (i in listaI.indices) {
                val m1 = (listaAtual[listaI[i]].x - listaAtual[init].x).pow(2)
                val m2 = (listaAtual[listaI[i]].y - listaAtual[init].y).pow(2)
                val d: Float = sqrt((m1 + m2))
                if (d < 50.dp.toPx()) {
                    posLD.add(listaI[i])
                }
            }

            for (i in 0..<posLD.size) {
                UpdateLimpar(listaAtual, posL, posLD[i])
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

        UpdateLimparRamos(listaAtual, posL, init)
        return posLIR
    }

    @Composable
    fun UpdateLimparRamos(listaAtual: MutableList<Offset3>, posL: List<Int>, init: Int) {


        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {

            if (posLIR.contains(init)) {


                return
            }
            posLIR.add(init)
            val listaI = posL.filter { !posLIR.contains(it) && it != 0 }
            val posLD: MutableList<Int> = mutableListOf()

            for (i in listaI.indices) {
                val m1 = (listaAtual[listaI[i]].x - listaAtual[init].x).pow(2)
                val m2 = (listaAtual[listaI[i]].y - listaAtual[init].y).pow(2)
                val d: Float = sqrt((m1 + m2))
                if (d < 60.dp.toPx()) {
                    posLD.add(listaI[i])
                }
            }
            if (init < 10 && posLD.size == 0) UpdateLimparRamos(listaAtual, posL, init + 1)
            for (i in 0..<posLD.size) {
                UpdateLimparRamos(listaAtual, posL, posLD[i])
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


    fun onZom() {
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

                listCoresR = listCores.filter { lista.contains(it) } as MutableList<Int>


            }
            return listCoresR
        }
    fun onFim(tocou:Boolean) {
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {

             if(!fim.isPlaying && tocou) {
                 fim.setVolume(0.2f, 0.2f)
                 //   fim.seekTo(0)

                 fim.start()
             }


        }
    }

    fun onMusica(continuar:Boolean,tipo:Int) {
        val coroutineScope = CoroutineScope(Dispatchers.Default)

        coroutineScope.run {


            if(tipo == 1) {
son=1
                if (!topb.isPlaying && continuar || reiniciarSom) {
                    reiniciarSom = false

                    topb.setVolume(0.5f, 0.5f)
                    topb.seekTo(0)
                    topb.start()
                } else if (!topb.isPlaying && !continuar) {

                    topb.pause()
                }
            }else{
son=2
                if(!top.isPlaying && continuar || reiniciarSom) {

                    reiniciarSom = false

                    top.setVolume(0.5f, 0.5f)
                    top.seekTo(0)
                    top.start()
                }else  if(!top.isPlaying && !continuar) {

                    top.pause()
                }

            }


        }
    }


fun continuarSon(){
    reiniciarSom=true

    if(son==1){

        onMusica(true,1)
    }else{

        onMusica(true,2)
    }
}





}
