package com.example.canvasteste.game.logic
import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.canvasteste.game.model.Cores
import com.example.canvasteste.R
class CCores(context: Context) {


    private val listCores = mutableListOf(R.drawable.red,R.drawable.blue,R.drawable.yaelow,R.drawable.green)
private val abilit = AAbilite(context)
private val default : Cores = Cores(setColors() )
private val ccores = MutableStateFlow(default)
    val kores: StateFlow<Cores> = ccores
     fun onUpdate(list:MutableList<Int>) {
         ccores.update { cores ->

             cores.copy(cores = list)
        }
    }
private fun setColors(): MutableList<Int> {
    val inny:Int = (0..3).random()
    val llt = mutableListOf(listCores[inny])
    for (i in 1..<abilit.abilite.value.pos.size) {
        val inny2: Int = (0..3).random()
        llt.add(listCores[inny2])
    }
  return llt
}


}