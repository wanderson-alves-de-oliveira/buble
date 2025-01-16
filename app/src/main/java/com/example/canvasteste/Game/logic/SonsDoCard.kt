package com.example.canvasteste.Game.logic

import android.content.Context
import android.media.MediaPlayer
import com.example.canvasteste.R

class SonsDoCard(context: Context) {

    val context: Context = context
     var plim: MediaPlayer = MediaPlayer.create(this.context, R.raw.finalyy)



    fun OnPlim() {



            plim.setVolume(0.2f, 0.2f)
           // plim.seekTo(0)

            plim.start()


    }

    fun OnPlim2() {



            plim.setVolume(0.2f, 0.2f)
               plim.seekTo(0)
            plim.start()

    }


    fun OnPlim3() {



            plim.setVolume(0.2f, 0.2f)
               plim.seekTo(0)
            plim.start()


    }

}