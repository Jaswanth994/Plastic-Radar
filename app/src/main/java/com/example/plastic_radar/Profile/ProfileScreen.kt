package com.example.plastic_radar.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*

import com.example.plastic_radar.Routes
import com.example.plastic_radar.homescreen.BottomNavigationBar
import com.example.plastic_radar.homescreen.TitleSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.HomeScreen) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF388E3C) // Deeper green for better contrast
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFF1F8E9)) // Light green with a yellow tint for a fresh look
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Profile Section
            item {
                ProfileCard(name = "ROHITH", phoneNumber = "9014977891")
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Wallet Section (Add your WalletCard or Wallet related composables here)
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Menu Items
            val items = listOf(
                Pair(Icons.Default.Public, "Change Country"),
                Pair(Icons.Default.Description, "Scrap Order History"),
                Pair(Icons.Default.Policy, "Policies"),
                Pair(Icons.Default.Info, "About Us"),
                Pair(Icons.Default.Star, "Rate Us"),
                Pair(Icons.Default.Call, "Call Us"),
                Pair(Icons.AutoMirrored.Filled.Chat, "Enquiry"),
                Pair(Icons.Default.Share, "Share App"),
                Pair(Icons.AutoMirrored.Filled.ExitToApp, "Log Out")
            )

            items(items) { item ->
                DashboardItem(
                    icon = item.first,
                    label = item.second,
                    onClick = {
                        when (item.second) {
                            "Change Country" -> navController.navigate(Routes.ChangeCountryScreen)
                            // Add other cases for different routes
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF81C784)) // Muted green with a hint of blue for buttons
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .border(width = 1.dp, color = Color(0xFF4CAF50)) // Add border to make buttons stand out
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

