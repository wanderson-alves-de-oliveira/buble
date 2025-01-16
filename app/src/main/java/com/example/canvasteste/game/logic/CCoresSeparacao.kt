package com.example.canvasteste.game.logic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.canvasteste.game.model.Cores
import com.example.canvasteste.R
class CCoresSeparacao {

    private val listCores = mutableListOf(R.drawable.red,R.drawable.blue,R.drawable.yaelow,R.drawable.pink)
private val default : Cores = Cores(setColors() )
private val ccores = MutableStateFlow(default)
    val coresStateFlow: StateFlow<Cores> = ccores
     fun onUpdate(list: MutableList<Int>) {
         ccores.update { cores ->

             cores.copy(cores = list)
        }
    }
private fun setColors(): MutableList<Int> {
    val inny:Int = (0..3).random()
    val llt = mutableListOf(listCores[inny])

  return llt
}
}