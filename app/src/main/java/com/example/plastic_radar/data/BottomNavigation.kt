package com.example.plastic_radar.data

import androidx.compose.ui.graphics.vector.ImageVector
data class BottomNavigation(
    val title: String,
    val iconRes: Int,  // Change this to Int to store drawable resource IDs
    val route: String
)