package com.example.canvasteste
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.canvasteste.Game.Game
import com.example.canvasteste.Game.InfiniteMapScreen
import com.example.canvasteste.Game.Mapa
import com.example.canvasteste.ui.theme.CanvasTesteTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContent {
            CanvasTesteTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,startDestination = "mapa") {
                    composable(route = "game/{param}",  arguments = listOf(navArgument("param") {
                        type = NavType.StringType // Especifica que o parâmetro será uma String
                    })){backStackEntry ->
                        val param = backStackEntry.arguments?.getString("param")
                      
                        Game(navController,baseContext,param) }

                    composable(route = "mapa") {


                    //    Mapa(navController,baseContext)

 InfiniteMapScreen(navController = navController ,baseContext)


                    }




                }




            }
        }
    }
}


