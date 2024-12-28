package com.example.canvasteste.Game.logic


import com.example.canvasteste.Game.model.Abilite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AAbilite( ) {

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
    36,37,38,39,40,41,42,43,
    66,
    67,
    69,
    89,
    90,
    91,
    125,
    129) )
private val _AAbilite = MutableStateFlow<Abilite>(default)
    val Abilite: StateFlow<Abilite> = _AAbilite


     fun onUpdate(list: List<Int>) {
         _AAbilite.update { abilite ->
            var new =  abilite.pos

             abilite.copy(pos = new)
        }
    }


    fun onUpdateRamos(list: List<Int>) {
        _AAbilite.update { abilite ->
            var new =  abilite.pos

            abilite.copy(posRamo = new)
        }
    }

}