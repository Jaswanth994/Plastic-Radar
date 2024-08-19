package com.example.plastic_radar

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plastic_radar.homescreen.HomeScreen
import com.example.plastic_radar.Profile.ChangeCountryScreen
import com.example.plastic_radar.Profile.ComingSoonScreen
import com.example.plastic_radar.Profile.ProfileScreen
import com.example.plastic_radar.Profile.ScrapOrderHistoryScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {
        composable(Routes.SplashScreen) {
            SplashScreen(navController)
        }
//        composable(Routes.AppContent) {
//            AppContent(navController,Firebase.auth)
//        }
        composable(Routes.AuthOrMainScreen) {
            AuthOrMainScreen(navController,Firebase.auth)
        }
        composable(Routes.HomeScreen) {
            HomeScreen(navController)
        }

        composable(Routes.StateSelectionScreen) {
            StateSelectionScreen(navController)
        }
        composable(Routes.DistrictSelectionScreen+"$/state") { backStackEntry ->
            val state = backStackEntry.arguments?.getString("state")
            if (state != null) {
                DistrictSelectionScreen(state)
            }
        }

        composable("profile") {
            ProfileScreen(navController)
        }
        composable("home"){
            HomeScreen(navController)
        }
        composable("dispose"){
            DisposeScreen(navController)
        }

        composable(Routes.onboardingScreen){
            OnboardingScreen(navController)
        }

        composable(Routes.ChangeCountryScreen){
            ChangeCountryScreen(navController)
        }

        composable(Routes.ScrapOrderHistoryScreen){
            ScrapOrderHistoryScreen(navController)
        }

        composable("collector"){
            CollectorScreen()
        }

        composable(Routes.ComingSoonScreen){
            ComingSoonScreen(onBackClick = {
                // Handle the back navigation, e.g., using NavController
                navController.navigateUp()
            })
        }
    }
}
