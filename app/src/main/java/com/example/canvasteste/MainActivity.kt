package com.example.canvasteste

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.canvasteste.Game.Game
import com.example.canvasteste.Game.Mapa

import com.example.canvasteste.ui.theme.CanvasTesteTheme



class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContent {
            CanvasTesteTheme {
//                      Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onSurface) {
//                         Game(baseContext)
//                       //   teste()
//                      }

                val navController = rememberNavController()
                NavHost(navController = navController,startDestination = "mapa") {

                    composable(route = "game") { Game(navController,baseContext) }
                    composable(route = "mapa") { Mapa(navController,baseContext) }
                }

            }
        }
    }
}
