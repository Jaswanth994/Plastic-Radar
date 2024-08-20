package com.example.plastic_radar.Profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.plastic_radar.Profile.DashboardItem
import com.example.plastic_radar.Routes
import com.example.plastic_radar.firbase.Order
import com.example.plastic_radar.firbase.OrderRepository
import com.example.plastic_radar.homescreen.BottomNavigationBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

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
                title = { Text("Profile",) },
//                backgroundColor = Color(0xFF2A8D8D),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF2A8D8D),  // Updated to match the theme color
                contentColor = Color.White
                )

        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA)) // Light background to match home page
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                // Profile Image
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
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
                Button(
                    onClick = {
                        // Navigate to the EditProfileScreen
                        navController.navigate(Routes.EditProfileScreen)
                    },
                    colors = ButtonDefaults.buttonColors( Color(0xFF2A8D8D))
                ) {
                    Text("Edit Profile", color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Menu Items (2 items per row)
            item {
                val items = listOf(

                    Triple(Icons.Default.Description, "Order History", Routes.ScrapOrderHistoryScreen),
                    Triple(Icons.Default.Info, "About Us", Routes.AboutUsScreen),
                    Triple(Icons.Default.Policy, "Policies", Routes.PoliciesScreen),
                    Triple(Icons.Default.Public, "Country", Routes.ComingSoonScreen),
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
                                        .background(Color(0xFF2A8D8D), RoundedCornerShape(12.dp))
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