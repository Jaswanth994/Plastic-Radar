package com.example.plastic_radar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChangeCountryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Text(text = "Select a Country", fontSize = 20.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // List of countries (For simplicity, only a few countries are listed)
        val countries = listOf("India", "United States", "Canada", "Australia", "United Kingdom")

        countries.forEach { country ->
            Text(
                text = country,
                fontSize = 18.sp,
                color = Color(0xFF008000),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFE0FFE0), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChangeCountryScreen() {
    ChangeCountryScreen()
}
