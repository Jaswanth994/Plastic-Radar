package com.example.plastic_radar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plastic_radar.homescreen.AutoScrollingCardCarousel
import com.example.plastic_radar.homescreen.BottomNavigationBar
import com.example.plastic_radar.homescreen.Heading1
import com.example.plastic_radar.homescreen.PlasticTypes
import com.example.plastic_radar.homescreen.TitleSection
import com.google.firebase.auth.FirebaseUser

//@Composable
//fun MainScreen(user: FirebaseUser, onSignOut: () -> Unit) {
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp)
//    ) {
//        val weltxt = Text(text = "Welcome, ${user.email}")
//
//        Button(
//            onClick = onSignOut,
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text(text = "Sign Out")
//
//        }
//    }
//}

//@Composable
//fun MainScreen(user: FirebaseUser, onSignOut: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally, // Center-align items horizontally within the Column
//        verticalArrangement = Arrangement.Center // Center items vertically within the Column
//    ) {
//        Text(
//            text = "Welcome, ${user.email}",
//            textAlign = TextAlign.Center, // Center-align text
//            modifier = Modifier.padding(bottom = 16.dp) // Add padding below the text
//        )
//
//        Button(
//            onClick = onSignOut,
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text(text = "Sign Out")
//        }
//    }
//}



@Preview
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TitleSection()
            AutoScrollingCardCarousel()
            Heading1()
            PlasticTypes()
            // Add other sections here...
        }
    }

}

