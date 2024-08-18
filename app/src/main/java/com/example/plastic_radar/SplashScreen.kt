package com.example.plastic_radar

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isOnboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)

    LaunchedEffect(Unit) {
        delay(2000) // Delay for splash screen duration
        if (isOnboardingShown) {
            // Navigate directly to the home screen if onboarding has already been shown
            navController.navigate(Routes.HomeScreen) {
                popUpTo(Routes.SplashScreen) { inclusive = true }
            }
        } else {
            // Navigate to the onboarding screen if it's the first launch
            navController.navigate(Routes.onboardingScreen) {
                popUpTo(Routes.SplashScreen) { inclusive = true }
            }
            // Set onboarding as shown
            with(sharedPreferences.edit()) {
                putBoolean("onboarding_shown", true)
                apply()
            }
        }
    }

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = TweenSpec(durationMillis = 500), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .scale(scale)
        )
    }
}