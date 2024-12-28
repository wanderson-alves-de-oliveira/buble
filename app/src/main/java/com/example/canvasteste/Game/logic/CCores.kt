package com.example.canvasteste.Game.logic


import android.media.Image
import androidx.compose.animation.core.AnimationVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.canvasteste.Game.model.Abilite
import com.example.canvasteste.Game.model.Player
import com.example.canvasteste.Game.model.Viewport
 import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.canvasteste.Game.logic.AAbilite
import com.example.canvasteste.Game.model.Cores
import com.example.canvasteste.R

class CCores( ) {
    val listCores = mutableListOf(R.drawable.red,R.drawable.blue,R.drawable.yaelow,R.drawable.pink)
val abilit = AAbilite()
private val default : Cores = Cores(setColors() )
private val _CCores = MutableStateFlow<Cores>(default)
    val Cores: StateFlow<Cores> = _CCores


     fun onUpdate(list:MutableList<Int>) {
         _CCores.update { cores ->
            var new =  list

             cores.copy(cores = new)
        }
    }

fun setColors(): MutableList<Int> {
    var inny:Int = (0..3).random()
    var llt = mutableListOf(listCores[inny])
    for (i in 1..abilit.Abilite.value.pos.size-1) {
        var inny: Int = (0..3).random()
        llt.add(listCores[inny])
    }
  return llt
}


}