package com.example.canvasteste.Game.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.canvasteste.Game.di.engeni.TimeManager
import com.example.canvasteste.Game.model.Viewport
    class GameDI(val timeManager: TimeManager) {
       companion object {
            @Composable
            fun rememberDI(viewport: Viewport): GameDI {
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
