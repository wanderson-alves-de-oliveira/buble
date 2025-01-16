package com.example.canvasteste.game.logic


import android.content.Context
import com.example.canvasteste.game.di.engeni.ferramentaUx.Offset3
import com.example.canvasteste.game.di.engeni.ferramentaUx.Tela

import com.example.canvasteste.game.model.Abilite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class AAbilite(context: Context ) {

      val tela : Tela = Tela(context)
    private val default : Abilite = Abilite(mutableListOf( 0,1,2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,14,
    15,
    16,
    17,
    18,
    19,
    20,
    21,
    22,
    23,
    24,
    25),mutableListOf( 0),setonUpdateMoveDefault()  )
private val AAbilite = MutableStateFlow(default)
    val abilite: StateFlow<Abilite> = AAbilite
     fun onUpdate(list: MutableList<Int>) {
         AAbilite.update { abilite ->


             abilite.copy(pos = list)
        }
    }
    fun onUpdateRamos(list: MutableList<Int>) {
        AAbilite.update { abilite ->


            abilite.copy(posRamo = list)
        }
    }
    fun onUpdateMove(list: MutableList<Offset3>) {
        AAbilite.update { abilite ->


            abilite.copy(posMove = list)
        }
    }
    fun onUpdateMoveReset(list: MutableList<Offset3>) {
        AAbilite.update { abilite ->


            abilite.copy(posMoveReset = list)
        }
    }
    fun onUpdateUltimaLinha(it : Int) {
        AAbilite.update { abilite ->


            abilite.copy(ultimaLinha = it)
        }
    }

    fun onUpdatePosRef(it : Offset3) {
        AAbilite.update { abilite ->


            abilite.copy(posRef = it)
        }
    }
    private fun setonUpdateMoveDefault() : MutableList<Offset3>{

        var interi = 0
        var interiy = 1
        val tam = 100f
        val tamMeio = 50f
        val litOffsetExtFI = mutableListOf(10, 31, 52, 73, 94, 115, 136, 157)
        val litOffsetExtFI2 = mutableListOf(21, 42, 63, 84, 105, 126, 147, 168)
        val m:Float =15 * tela.densidade
        val litOffset = MutableList(179) { Offset3(0f, 1000f, false, 0) }
        for (i in 0..178) {
            var xp = tam *interi
            var yp = tam *interiy
            if (interiy % 2 == 0) {
                if (litOffsetExtFI2.contains(i)) {
                    interi = 0
                    interiy++
                    xp = tamMeio
                    yp = tam * interiy
                }
            } else {
                xp += tamMeio
                if (litOffsetExtFI.contains(i)) {
                    interi = 0
                    interiy++
                    xp = 0f
                    yp = tam *interiy
                }
            }
            val vazio = true

            val offf = Offset3(
                 xp + m,
                yp + m,
                vazio,
                i,
                interiy
            )
            litOffset[i] = offf
            interi++
        }
        return litOffset
    }
}