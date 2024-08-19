package com.example.plastic_radar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun PostSignInSplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(1000) // Delay for 2 seconds (or any duration you prefer)
        navController.navigate(Routes.HomeScreen) {
            popUpTo(Routes.onboardingScreen) { inclusive = true } // Clear previous screens from backstack
        }
    }
    SplashScreenUI() // Call your splash screen UI
}
