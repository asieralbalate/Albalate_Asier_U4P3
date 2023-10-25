package com.example.albalate_asier_u4p3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.albalate_asier_u4p3.ui.theme.Albalate_Asier_U4P3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Albalate_Asier_U4P3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Portada") {
                        composable("Portada") { Portada(navController, snackbarHostState = SnackbarHostState()) }
                        composable("Filled.Email") { Email(navController, snackbarHostState =  SnackbarHostState()) }
                        composable("Filled.Info") { Info(navController, snackbarHostState =  SnackbarHostState()) }
                        composable("Filled.Build") { Portada(navController, snackbarHostState =  SnackbarHostState()) }

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Albalate_Asier_U4P3Theme {

    }
}