package com.example.canvasteste.Game.logic


import android.content.Context
import com.example.canvasteste.Game.di.engeni.ferramentas.Tela
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.canvasteste.Game.model.Cores
import com.example.canvasteste.R

class CCoresSeparacao(context: Context) {
    val context: Context = context
    val listCores = mutableListOf(R.drawable.red,R.drawable.blue,R.drawable.yaelow,R.drawable.pink)
val abilit = AAbilite(context)
private val default : Cores = Cores(setColors() )
private val _CCores = MutableStateFlow<Cores>(default)
    val Cores: StateFlow<Cores> = _CCores


     fun onUpdate(list: MutableList<Int>) {
         _CCores.update { cores ->
            var new =  list

             cores.copy(cores = new)
        }
    }

fun setColors(): MutableList<Int> {
    var inny:Int = (0..3).random()
    var llt = mutableListOf(listCores[inny])

  return llt
}


}