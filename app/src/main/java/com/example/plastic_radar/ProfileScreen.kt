package com.example.plastic_radar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plastic_radar.homescreen.BottomNavigationBar
import com.example.plastic_radar.homescreen.TitleSection

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = { TitleSection(navController) },  // Using your custom TopBar
        bottomBar = { BottomNavigationBar(navController) },  // Using your custom BottomNavigationBar
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF5FFF5))
            ) {
                // Content
                ContentList()
            }
        }
    )
}


@Composable
fun ContentList() {
    val items = listOf(
        Pair(Icons.Default.Business, "Merchants"),
        Pair(Icons.Default.Book, "Journals"),
        Pair(Icons.Default.Policy, "Policies"),
        Pair(Icons.Default.Info, "About Us"),
        Pair(Icons.Default.Star, "Rate Us"),
        Pair(Icons.Default.Call, "Call Us"),
        Pair(Icons.AutoMirrored.Filled.Chat, "Enquiry"),
        Pair(Icons.Default.Share, "Share App"),
        Pair(Icons.AutoMirrored.Filled.ExitToApp, "Log Out")
    )

    Column(modifier = Modifier.padding(16.dp)) {
        items.forEach { item ->
            DashboardItem(icon = item.first, label = item.second)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DashboardItem(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0FFE0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color(0xFF008000), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 18.sp, color = Color(0xFF008000))
    }
}

