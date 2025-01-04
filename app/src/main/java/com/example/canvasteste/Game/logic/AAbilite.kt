package com.example.canvasteste.Game.logic


import android.content.Context
import com.example.canvasteste.Game.di.engeni.ferramentas.Offset3
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela

import com.example.canvasteste.Game.model.Abilite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class AAbilite(context: Context ) {
     val context: Context = context
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
    25,
    26,
    27,
    28,
    29,
    30,
    31,
    32,
    33,
    34,
    35,
    36,37,38,39,40,41,42,43,46,47,
    56,57,58,59,60,
    66,
    67,
    69,
    80,81,82,83,
    89,
    90,
    91,100,101,110,111,112,113,118,119,120,122,123,124,
    125,
    129),mutableListOf( 0),setonUpdateMoveDefault()  )
private val _AAbilite = MutableStateFlow<Abilite>(default)
    val Abilite: StateFlow<Abilite> = _AAbilite
     fun onUpdate(list: MutableList<Int>) {
         _AAbilite.update { abilite ->
            var new =  list

             abilite.copy(pos = new)
        }
    }
    fun onUpdateRamos(list: MutableList<Int>) {
        _AAbilite.update { abilite ->
            var new =  list

            abilite.copy(posRamo = new)
        }
    }
    fun onUpdateMove(list: MutableList<Offset3>) {
        _AAbilite.update { abilite ->
            var new = list

            abilite.copy(posMove = new)
        }
    }
    fun onUpdateMoveReset(list: MutableList<Offset3>) {
        _AAbilite.update { abilite ->
            var new = list

            abilite.copy(posMoveReset = new)
        }
    }
    fun onUpdateUltimaLinha(it : Int) {
        _AAbilite.update { abilite ->
            var new = it

            abilite.copy(ultimaLinha = new)
        }
    }

    fun onUpdatePosRef(it : Offset3) {
        _AAbilite.update { abilite ->
            var new = it

            abilite.copy(posRef = new)
        }
    }
    fun setonUpdateMoveDefault() : MutableList<Offset3>{

        var interi = 0
        var interiy = 1
        var tam = 100f
        var tamMeio = 50f
        var litOffsetExtFI = mutableListOf<Int>(10, 31, 52, 73, 94, 115, 136, 157)
        var litOffsetExtFI2 = mutableListOf<Int>(21, 42, 63, 84, 105, 126, 147, 168)
        var litOffsetExtS = mutableListOf<Int>()
        var m:Float =15 * tela.densidade
        var litOffset = MutableList<Offset3>(179) { Offset3(0f, 1000f, false, 0) }
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
            var vazio = true

            var offf: Offset3 = Offset3(
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