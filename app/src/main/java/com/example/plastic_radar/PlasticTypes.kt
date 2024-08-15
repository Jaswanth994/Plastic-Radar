package com.example.plastic_radar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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


@Preview
@Composable
fun PlasticTypes() {
    // Sample Data (Replace with your actual data)
    val plasticWastes = listOf(
        PlasticWasteItem(R.drawable.pet, "PET", "$1.2/kg"),
        PlasticWasteItem(R.drawable.softplastic, "Soft Plastics", "$0.8/kg"),
        PlasticWasteItem(R.drawable.pvc,"PVC", "$1.0/kg"),
        PlasticWasteItem(R.drawable.watertank, "Water Tank", "$0.7/kg"),
        PlasticWasteItem(R.drawable.hardplastic, "Hard Plastic", "$1.1/kg"),
//        PlasticWasteItem(R.drawable.PS, "PS", "$0.9/kg"),
//        PlasticWasteItem(R.drawable.ABS, "ABS", "$1.5/kg"),
        PlasticWasteItem(R.drawable.nylon, "Nylon", "$2.0/kg"),
        PlasticWasteItem(R.drawable.acrylic, "Acrylic", "$1.3/kg")
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