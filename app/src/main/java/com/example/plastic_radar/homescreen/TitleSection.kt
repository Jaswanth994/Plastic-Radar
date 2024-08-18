package com.example.plastic_radar.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plastic_radar.Routes
import com.example.plastic_radar.firbase.getStateFromFirebase
import com.example.plastic_radar.firbase.saveStateToFirebase
import com.example.plastic_radar.ui.theme.Teal

@Composable
fun TitleSection(navController: NavController){
    var location by remember { mutableStateOf("Location") }
    LaunchedEffect(Unit) {
        getStateFromFirebase { retrievedState ->
            location = retrievedState
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column{
            Text(
                text="Plastic Radar",
                fontSize =24.sp,
                color = Teal,
                fontWeight = FontWeight.Bold,
//                style = MaterialTheme.typography.titleLarge
            )
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .background(Teal,
                    shape = RoundedCornerShape(26.dp))
        ) {
            Row(
                modifier = Modifier
                    .clickable{
                        navController.navigate(Routes.StateSelectionScreen) {
                            popUpTo(Routes.HomeScreen){ inclusive = false; }
                        }
                    }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = location,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }


    }
}