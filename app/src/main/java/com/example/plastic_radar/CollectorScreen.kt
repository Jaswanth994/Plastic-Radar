package com.example.plastic_radar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plastic_radar.firebase.Order
import com.example.plastic_radar.firebase.OrderRepository
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CollectorScreen() {
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
                title = { Text("Collector Screen") },
                backgroundColor = Color(0xFF6200EE),
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
            .background(Color(0xFFBB86FC), shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )
        }
    }
}

@Composable
fun OrderList(orders: List<Order>) {
    Column {
        for (order in orders) {
            OrderCard(order = order)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        backgroundColor = Color(0xFF6200EE),
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${order.name}", fontSize = 20.sp, color = Color.White)
            Text("Phone: ${order.phoneNumber}", fontSize = 16.sp, color = Color.White)
            Text("Address: ${order.addressName}", fontSize = 16.sp, color = Color.White)
            Text("City: ${order.city}", fontSize = 16.sp, color = Color.White)
            Text("Options: ${order.selectedOptions.joinToString()}", fontSize = 16.sp, color = Color.White)
            Text("Quantity: ${order.quantity.joinToString()}", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCollectorScreen() {
    CollectorScreen()
}