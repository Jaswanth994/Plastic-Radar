package com.example.plastic_radar.Profile

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.plastic_radar.Routes
import com.example.plastic_radar.homescreen.BottomNavigationBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(currentUser?.email ?: "") }
    var role by remember { mutableStateOf("") } // To store the user role (e.g., collector)
    var profileImageUrl by remember { mutableStateOf("") }

    // Fetch user's profile data from Firestore
    LaunchedEffect(Unit) {
        currentUser?.uid?.let { userId ->
            db.collection("users").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        firstName = document.getString("firstName") ?: ""
                        lastName = document.getString("lastName") ?: ""
                        role = document.getString("role") ?: ""
                        profileImageUrl = document.getString("profileImageUrl") ?: ""
                    }
                }
                .addOnFailureListener { exception ->
                    firstName = "User12"
                    // Handle any errors here
                }
        }
    }

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
                    containerColor = Color(0xFF1976D2)
                )
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFF191970))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                // Profile Image
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .clickable {
                            // Navigate to the EditProfileImageScreen for uploading a new profile image
                            navController.navigate(Routes.EditProfileImageScreen)
                        }
                ) {
                    if (profileImageUrl.isNotEmpty()) {
                        // Load and display the profile image from the URL
                        Image(
                            painter = rememberAsyncImagePainter(profileImageUrl),
                            contentDescription = "Profile Image",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Display user's first name and last name
                Text(
                    text = "$firstName $lastName",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
                )
                // Display user's email
                Text(
                    text = email,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                // Display role if the user is a collector
                if (role == "collector") {
                    Text(
                        text = "You are a Collector",
                        fontSize = 16.sp,
                        color = Color.Green
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Button to edit profile
                Button(onClick = {
                    // Navigate to the EditProfileScreen
                    navController.navigate(Routes.EditProfileScreen)
                }) {
                    Text("Edit Profile")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Menu Items (2 items per row)
            item {
                val items = listOf(
                    Triple(Icons.Default.Public, "Change Country", Routes.ChangeCountryScreen),
                    Triple(Icons.Default.Description, "Scrap Order History", Routes.ScrapOrderHistoryScreen),
                    Triple(Icons.Default.Policy, "Policies", Routes.ComingSoonScreen),
                    Triple(Icons.Default.Info, "About Us", Routes.ComingSoonScreen),
                    Triple(Icons.Default.Star, "Rate Us", Routes.ComingSoonScreen),
                    Triple(Icons.Default.Call, "Call Us", Routes.ComingSoonScreen),
                    Triple(Icons.Default.Chat, "Enquiry", Routes.ComingSoonScreen),
                    Triple(Icons.Default.Share, "Share App", Routes.ComingSoonScreen),
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
                                        when (item.second) {
                                            "Log Out" -> {
                                                logoutUser {
                                                    navController.navigate(Routes.AuthOrMainScreen) {
                                                        popUpTo(Routes.HomeScreen) { inclusive = true }
                                                    }
                                                }
                                            }
                                            else -> {
                                                navController.navigate(item.third ?: Routes.ComingSoonScreen)
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(4.dp)
                                        .background(Color(0xFF64B5F6), RoundedCornerShape(12.dp))
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

fun logoutUser(onLogoutSuccess: () -> Unit) {
    FirebaseAuth.getInstance().signOut()
    onLogoutSuccess()
}
