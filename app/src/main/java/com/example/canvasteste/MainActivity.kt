package com.example.canvasteste

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.canvasteste.Game.Game
import com.example.canvasteste.Game.InfiniteMapScreen
import com.example.canvasteste.Game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.Game.logic.PlayLogic
import com.example.canvasteste.Game.model.Viewport
import com.example.canvasteste.ui.theme.CanvasTesteTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContent {
            CanvasTesteTheme {

                val viewPort = remember {
                    Viewport(40.dp, 40.dp)
                }
                val di = rememberDI(viewPort)
                val playerLogic = PlayLogic(viewPort, this)
                var music: Int by remember { mutableStateOf(0) }

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mapa") {
                    composable(route = "game/{param}", arguments = listOf(navArgument("param") {
                        type = NavType.StringType // Especifica que o parâmetro será uma String
                    })) { backStackEntry ->
                        val param = backStackEntry.arguments?.getString("param")
                        LaunchedEffect(key1 = Unit) {
                            di.timeManager.deltaTime.collect { it ->

                                if( playerLogic.topb.isPlaying) {
                                    playerLogic.topb.pause()

                                }
                                    playerLogic.OnMusica(true)

                            }
                        }
                        Game(navController, baseContext, param)
                    }

                    composable(route = "mapa") {

                        LaunchedEffect(key1 = Unit) {
                            di.timeManager.deltaTime.collect { it ->

                                    playerLogic.OnMusicaB(true)
                                if( playerLogic.top.isPlaying) {
                                    playerLogic.top.pause()

                                }
                            }
                        }
                        InfiniteMapScreen(navController = navController, baseContext)


                    }


                }




            }
        }
    }
}


