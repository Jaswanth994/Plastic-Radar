package com.example.plastic_radar.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.plastic_radar.Routes
import com.example.plastic_radar.homescreen.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home",
                            tint = Color.Blue
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF1976D2) // Dark blue for the top bar background, including behind the arrow
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFE3F2FD)) // Light blue background
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Image and Name
            item {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "ROHITH",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                Text(
                    text = "@Rookie123",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Menu Items (2 items per row)
            item {
                val items = listOf(
                    Triple(Icons.Default.Public, "Change Country", Routes.ChangeCountryScreen),
                    Triple(Icons.Default.Description, "Scrap Order History", Routes.ScrapOrderHistoryScreen),
                    Triple(Icons.Default.Policy, "Policies", null),
                    Triple(Icons.Default.Info, "About Us", null),
                    Triple(Icons.Default.Star, "Rate Us", null),
                    Triple(Icons.Default.Call, "Call Us", null),
                    Triple(Icons.Default.Chat, "Enquiry", null),
                    Triple(Icons.Default.Share, "Share App", null),
                    Triple(Icons.Default.ExitToApp, "Log Out", null)
                )

                Column {
                    items.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            rowItems.forEach { item ->
                                DashboardItem(
                                    icon = item.first,
                                    label = item.second,
                                    onClick = {
                                        item.third?.let { route ->
                                            navController.navigate(route)
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp)
                                        .background(Color(0xFF64B5F6), RoundedCornerShape(12.dp)) // Blue background
                                        .padding(vertical = 18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}