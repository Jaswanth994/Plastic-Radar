package com.example.plastic_radar

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.plastic_radar.homescreen.PlasticWasteCard
import com.example.plastic_radar.ui.theme.Teal


// Replace these drawable resources with actual drawable resources from your project.
val keralaIcon = R.drawable.kerala
val tamilnaduIcon = R.drawable.tamilnadu
val karnatakaIcon = R.drawable.karnataka
val andhraIcon = R.drawable.andhrapradhesh
val telanganaIcon = R.drawable.telangana
val maharashtraIcon = R.drawable.maharashtra
val punjabIcon = R.drawable.punjab
val haryanaIcon = R.drawable.haryana
//@Preview
@Composable
fun StateSelectionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SELECT STATE",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val states = listOf(
            "Kerala" to keralaIcon,
            "Tamilnadu" to tamilnaduIcon,
            "Karnataka" to karnatakaIcon,
            "Andhra Pradesh" to andhraIcon,
            "Telangana" to telanganaIcon,
            "Maharashtra" to maharashtraIcon,
            "Punjab" to punjabIcon,
            "Haryana" to haryanaIcon
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(states) { (state, icon) ->
                StateCard(state = state, icon = icon,navController=navController)
            }
        }
    }
//
//        states.chunked(1).forEach { row ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                row.forEach { (state, icon) ->
//                    StateCard(state = state, icon = icon) {
//                      navController.navigate(Routes.DistrictSelectionScreen + "/$state")  {
//                        popUpTo("state_selection") { inclusive = false }
//                    }
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(2.dp))
//        }
//    }
}

@Composable
fun StateCard(state: String, icon: Int,navController: NavController) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(horizontal = 8.dp,vertical = 8.dp)
            .clickable {
                navController.navigate(Routes.DistrictSelectionScreen + "/$state")
             },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = state,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = state.uppercase(),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
