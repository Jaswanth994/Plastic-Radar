//package com.example.plastic_radar
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//@Composable
//fun IndianStatesAndDistrictsGrid() {
//    val states = listOf(
//        "Kerala", "Tamilnadu", "Karnataka", "Andhra Pradesh",
//        "Telangana", "Maharashtra", "Punjab", "Gujarat"
//    )
//
//    var selectedState by remember { mutableStateOf<String?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text("SELECT STATE", style = MaterialTheme.typography.headlineLarge)
//        Spacer(modifier = Modifier.height(16.dp))
//        LazyColumn {
//            items(states) { state ->
//                StateButton(
//                    stateName = state,
//                    iconResId = R.drawable.ic_state_icon, // Ensure this resource exists
//                    isSelected = state == selectedState,
//                    onClick = { selectedState = if (selectedState == state) null else state }
//                )
//                if (state == selectedState) {
//                    // Display the districts when a state is selected
//                    DistrictsList(state)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun StateButton(
//    stateName: String,
//    iconResId: Int,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    // Example implementation for StateButton
//    Button(
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = if (isSelected) Color.Gray else Color.White
//        ),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(painterResource(id = iconResId), contentDescription = null)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(stateName)
//        }
//    }
//}
////
////@Composable
////fun DistrictsList(state: String) {
////    // Placeholder implementation for DistrictsList
////    // Replace this with actual district data based on the selected state
////    Text(
////        text = "Districts of $state",
////        style = MaterialTheme.typography.bodyMedium
////    )
////}
//
//
//@Composable
//fun DistrictsList(state: String) {
//    // Replace with actual districts data for each state
//    val districts = when (state) {
//        "Kerala" -> listOf("Ernakulam", "Trivandrum", "Kozhikode", "Thrissur")
//        // Add other states' districts here
//        else -> emptyList()
//    }
//
//    Spacer(modifier = Modifier.height(16.dp))
//    Text("Districts in $state:")
//    Spacer(modifier = Modifier.height(8.dp))
//    districts.forEach { district ->
//        Text(district)
//    }
//}
//
//// Usage:
//// IndianStatesAndDistrictsGrid()
