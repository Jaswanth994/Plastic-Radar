package com.example.plastic_radar.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.plastic_radar.firbase.Order
import com.example.plastic_radar.firbase.OrderRepository



@Composable
fun ScrapOrderHistoryScreen(navController: NavController) {
    var orders by remember { mutableStateOf<List<Order>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Fetch the orders using LaunchedEffect
    LaunchedEffect(Unit) {
        OrderRepository.fetchOrders(
            onSuccess = { orderList ->
                orders = orderList
                loading = false
            },
            onFailure = { exception ->
                error = exception.message
                loading = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Order History", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Home",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF2A8D8D),  // Updated to match the theme color
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        when {
            loading -> {
                // Show a loading indicator while fetching data
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                // Show an error message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${error ?: "Unknown error"}")
                }
            }
            else -> {
                // Display the orders in a LazyColumn
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(paddingValues)
                ) {
                    items(orders) { order ->
                        OrderCard(order)
                    }
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        backgroundColor = Color(0xFF2A8D8D), // Updated to match the theme color
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp, // Increased elevation for a more pronounced shadow
        shape = RoundedCornerShape(20.dp) // Custom rounded corners
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${order.name}", fontWeight = FontWeight.Bold)
            Text(text = "Phone: ${order.phoneNumber}")
            Text(text = "Address: ${order.addressName}, ${order.city}")
            Text(text = "Pick-Up-Date: ${order.scheduleDate}")
            Text(text = "Type: ${order.selectedOptions.joinToString(", ")}")
            Text(text = "Quantity: ${order.quantity.joinToString(", ")}")
        }
    }
}