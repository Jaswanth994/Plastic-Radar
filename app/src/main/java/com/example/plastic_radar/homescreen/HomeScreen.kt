package com.example.plastic_radar.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TitleSection(navController)
            AutoScrollingCardCarousel()
            Heading1()
            PlasticTypes()
            // Add other sections here...
        }
    }

}