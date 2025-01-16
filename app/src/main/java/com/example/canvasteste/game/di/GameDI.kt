package com.example.canvasteste.game.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.canvasteste.game.di.engeni.TimeManager

class GameDI(val timeManager: TimeManager) {
       companion object {
            @Composable
            fun rememberDI(): GameDI {
                val coroutineScope = rememberCoroutineScope()
                val timeManager = remember {
                    TimeManager(coroutineScope)
                }
                return remember {
                    GameDI(timeManager)
                }
            }
       }
    }
