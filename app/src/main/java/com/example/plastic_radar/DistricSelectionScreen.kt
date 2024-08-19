package com.example.plastic_radar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define your district map with states and their respective districts
val districtMap = mapOf(
    "Kerala" to listOf("Ernakulam", "Kozhikode", "Trivandrum", "Thrissur"),
    "Tamilnadu" to listOf("Chennai", "Coimbatore", "Madurai", "Trichy")
    // Add other states and districts here
)

@Composable
fun DistrictSelectionScreen(state: String) {
    val districts = districtMap[state] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SELECT DISTRICT",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black

        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(districts) { district ->
                DistrictCard(districtName = district)
            }
        }


//        districts.chunked(2).forEach { row ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                row.forEach { district ->
//                    DistrictCard(districtName = district) {
//                        // Handle click event for district
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//        }
    }
}

@Composable
fun DistrictCard(districtName: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(60.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1B5E20)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = districtName.uppercase(),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp)
            )
        }
    }
}
