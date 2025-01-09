package com.example.canvasteste.Game.di.engeni.ferramentas

import com.example.canvasteste.Game.logic.CCores
import com.example.canvasteste.R

class Formas() {


    var litOffsetExtFI = mutableListOf<Int>(42, 73, 105, 136)

    val flores = mutableListOf<Int>(
          1,1,1,1,1,1,1,1,1,1,//null
        0,0,1,1,0,1,1,0,1,1,0,
         0,1,0,1,1,0,1,1,0,1,
        0,0,1,1,0,1,1,0,1,1,0,
        0,0,1,1,0,1,1,0,1,1,0,
        0,1,0,1,1,0,1,1,0,1,
        0,0,1,1,0,1,1,0,1,1,0,
         0,1,1,0,1,1,0,1,1,0,
         0,1,0,1,1,0,1,1,0,1,0,
        0,1,1,0,1,1,0,1,1,0,
        0,1,1,0,1,1,0,1,1,0,0,
          1,0,1,1,0,1,1,0,1,0,
        0,1,1,0,1,1,0,1,1,0,0,
          0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
          0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0
    )

    val floress = mutableListOf<Int>(
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,0,0,0
    )




    fun pegarFlores():MutableList<MutableList<Int>>{


        val listCores = mutableListOf(R.drawable.red, R.drawable.blue, R.drawable.yaelow, R.drawable.green)
        var floresX:MutableList<Int> = mutableListOf()
        for(i in 0..flores.size-1){

            if(flores[i]==1){
                floresX.add(i)
            }
        }
        var floresXC :MutableList<Int> = mutableListOf()

        var inny:Int = (0..3).random()
        var nivel:Int = 1
        var j:Int = 1

        for (ii in 0..floresX.size-1) {
            if(nivel==1){
                if(litOffsetExtFI.contains(ii-1)){
                    inny = (0..3).random()
                }

            }else{
              inny = (0..3).random()
            }
            floresXC.add(listCores[inny])
        }

        var cont:MutableList<MutableList<Int>> = mutableListOf()
        cont.add(floresX)
        cont.add(floresXC)
        return cont

    }










}