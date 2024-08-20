package com.example.plastic_radar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plastic_radar.firbase.Order
import com.example.plastic_radar.firbase.OrderRepository
//import com.example.plastic_radar.firbase.Order
//import com.example.plastic_radar.firbase.OrderRepository
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CollectorScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var orders by remember { mutableStateOf(listOf<Order>()) }
    var filteredOrders by remember { mutableStateOf(listOf<Order>()) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            filteredOrders = orders.filter { it.city.contains(searchQuery, ignoreCase = true) }
        } else {
            filteredOrders = orders
        }
    }

    LaunchedEffect(Unit) {
        OrderRepository.fetchAllOrders(
            onSuccess = { fetchedOrders ->
                orders = fetchedOrders
                filteredOrders = fetchedOrders
            },
            onFailure = { exception ->
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Error fetching orders: ${exception.message}")
                }
            }
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Welcome Collector !!") },
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OrderList(orders = filteredOrders)
        }
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF), shape = MaterialTheme.shapes.medium)
            .border(1.dp, Color.Black) // Added black outline
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon", tint = Color(0xFF00897B)) // Updated to match the theme color
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    color = Color(0xFF00897B), // Updated text color to match theme
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            "Search here",
                            style = LocalTextStyle.current.copy(
                                color = Color(0x8000897B), // Transparent placeholder
                                fontSize = 18.sp
                            )
                        )
                    }
                    innerTextField() // The actual input field
                }
            )
        }
    }
}

@Composable
fun OrderList(orders: List<Order>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(orders) { order ->
            OrderCard(order = order)
            Spacer(modifier = Modifier.height(8.dp))
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
        Column(
            modifier = Modifier.padding(16.dp) // Added padding inside the card for better alignment
        ) {
            Row {
                Text(
                    text = "Name:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.name,
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "Phone:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.phoneNumber,
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "Address:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.addressName,
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "City:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.city,
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "Pick-Up-Date:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.scheduleDate,
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "Options:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.selectedOptions.joinToString(),
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
            Row {
                Text(
                    text = "Quantity:",
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White,
                    modifier = Modifier.width(120.dp)
                )
                Text(
                    text = order.quantity.joinToString(),
                    fontSize = 16.sp, // Reduced font size
                    color = Color.White
                )
            }
        }
    }
}