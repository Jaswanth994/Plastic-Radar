package com.example.plastic_radar
import com.example.plastic_radar.R
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreenUI() {
    // Animation state
    val size = animateDpAsState(
        targetValue = 200.dp,
        animationSpec = tween(durationMillis = 2000) // Adjust duration as needed
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pla), // Replace with your actual logo resource
            contentDescription = "App Logo",
            modifier = Modifier
                .size(size.value) // Apply scale animation
        )
        Spacer(modifier = Modifier.height(16.dp)) // Space between logo and text
        Text(
            text = "Welcome to Plastic Radar",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
    }
}
