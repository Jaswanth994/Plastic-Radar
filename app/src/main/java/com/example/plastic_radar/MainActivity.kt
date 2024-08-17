package com.example.plastic_radar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "auth") {
                composable("auth") {
                    AuthScreen { signedInUser ->
                        // Navigate to the profile screen after successful sign-in or sign-up
                        navController.navigate("profile/${signedInUser.email}")
                    }
                }
                composable("profile/{email}") { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email") ?: ""
                    ProfileScreen(email)
                }
            }
        }
    }
}
