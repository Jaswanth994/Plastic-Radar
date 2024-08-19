package com.example.plastic_radar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreenUI() {
    // Animation states for scale and rotation
    val scale = remember { mutableStateOf(1f) }
    val rotation = remember { mutableStateOf(0f) }

    // Launch an effect to animate values
    LaunchedEffect(Unit) {
        delay(500) // Wait for a moment before starting the animation
        scale.value = 1.5f
        rotation.value = 360f
    }

    // Animated values using animateFloatAsState
    val animatedScale = animateFloatAsState(
        targetValue = scale.value,
        animationSpec = tween(durationMillis = 2000)
    )
    val animatedRotation = animateFloatAsState(
        targetValue = rotation.value,
        animationSpec = tween(durationMillis = 2000)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash), // Replace with your actual logo resource
            contentDescription = "App Logo",
            modifier = Modifier
                .size(200.dp) // Set the initial size
                .graphicsLayer(
                    scaleX = animatedScale.value, // Apply scale animation on X axis
                    scaleY = animatedScale.value, // Apply scale animation on Y axis
                    rotationZ = animatedRotation.value // Apply rotation animation
                )
        )
        Spacer(modifier = Modifier.height(16.dp)) // Space between logo and text
        Text(
            text = "Welcome to Plastic Radar",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
            color = Color.Black
        )
    }
}
