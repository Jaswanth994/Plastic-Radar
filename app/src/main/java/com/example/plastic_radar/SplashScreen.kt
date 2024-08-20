package com.example.plastic_radar

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(navController: NavController) {
    var showText by remember { mutableStateOf(false) }
    var logoAnimationComplete by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isOnboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)
    val isUserSignedIn = sharedPreferences.getBoolean("user_signed_in", false) // Assuming you store this flag when the user signs in

    // Rotation and scaling animation
    val rotationAndScaleAnimation = rememberInfiniteTransition()
    val rotation by rotationAndScaleAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val scale by rotationAndScaleAnimation.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        delay(3000)
        logoAnimationComplete = true
        showText = true
        delay(1500)
        if (isOnboardingShown ) {
            navController.navigate(Routes.AuthOrMainScreen) { // Navigate to home screen if onboarding is shown and user is signed in
                popUpTo(Routes.SplashScreen) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.onboardingScreen) { // Otherwise, show onboarding
                popUpTo(Routes.SplashScreen) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F4F4F)),
        contentAlignment = Alignment.Center
    ) {
        if (!logoAnimationComplete) {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .rotate(rotation)
                    .scale(scale)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.applogo),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
        }

        if (showText) {
            Text(
                text = "PLASTIC RADAR",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
            )
        }
    }
}
