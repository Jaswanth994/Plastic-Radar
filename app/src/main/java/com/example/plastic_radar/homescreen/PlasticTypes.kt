package com.example.plastic_radar.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plastic_radar.R


@Preview
@Composable
fun PlasticTypes() {
    // Sample Data (Replace with your actual data)
    val plasticWastes = listOf(
        PlasticWasteItem(R.drawable.pet, "PET", "Rs.10/kg"),
        PlasticWasteItem(R.drawable.softplastic, "Soft Plastics", "Rs.8/kg"),
        PlasticWasteItem(R.drawable.pvc,"PVC", "Rs.15/kg"),
        PlasticWasteItem(R.drawable.watertank, "Water Tank", "Rs.20/kg"),
        PlasticWasteItem(R.drawable.hardplastic, "Hard Plastic", "Rs.25/kg"),
     PlasticWasteItem(R.drawable.ps, "PS", "Rs.13/kg"),
        PlasticWasteItem(R.drawable.abs, "ABS", "Rs.15/kg"),
        PlasticWasteItem(R.drawable.nylon, "Nylon", "Rs.10/kg"),
        PlasticWasteItem(R.drawable.acrylic, "Acrylic", "Rs.20/kg")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(plasticWastes) { item ->
            PlasticWasteCard(item)
        }
    }
}

@Composable
fun PlasticWasteCard(item: PlasticWasteItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .size(120.dp) // Adjust the card size as needed
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFE0F7FA))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom= 8.dp)
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = item.cost,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

data class PlasticWasteItem(val imageRes: Int, val name: String, val cost: String)