package com.example.canvasteste

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.canvasteste.game.Game
import com.example.canvasteste.game.InfiniteMapScreen
import com.example.canvasteste.game.di.GameDI.Companion.rememberDI
import com.example.canvasteste.game.logic.PlayLogic
import com.example.canvasteste.game.model.Viewport
import com.example.canvasteste.ui.theme.CanvasTesteTheme

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {


    lateinit var playerLogic: PlayLogic
      var  son = 0
    var continuarSon = true
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //coloca em fullscreen
        this.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
         playerLogic = PlayLogic(Viewport(40.dp, 40.dp), this)

        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {
            CanvasTesteTheme {


                val di = rememberDI()

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mapa") {
                    composable(route = "game/{param}", arguments = listOf(navArgument("param") {
                        type = NavType.StringType // Especifica que o parâmetro será uma String
                    })) { backStackEntry ->
                        val param = backStackEntry.arguments?.getString("param")
                        LaunchedEffect(key1 = Unit) {

                            son =2
                            di.timeManager.deltaTime.collect {

                                if( playerLogic.topb.isPlaying) {
                                    playerLogic.topb.pause()

                                }
                                    playerLogic.onMusica(continuarSon,2)

                            }
                        }
                        Game(navController, baseContext, param)
                    }

                    composable(route = "mapa") {

                        LaunchedEffect(key1 = Unit) {


                            son =1
                            di.timeManager.deltaTime.collect {
                                playerLogic.onMusica(continuarSon,1)
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

    override fun onResume() {
        super.onResume()
if(son>0) {
    continuarSon = true
    playerLogic.continuarSon()
}


    }
    override fun onPause() {
        super.onPause()
        continuarSon = false
        if( playerLogic.top.isPlaying) {

            playerLogic.top.pause()

        }else   if( playerLogic.topb.isPlaying) {

            playerLogic.topb.pause()

        }

    }







}


