package com.example.plastic_radar.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plastic_radar.R
//import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About Us", fontSize = 20.sp, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Profile",
                            tint = Color.Black
                        )
                    }
                },
//                backgroundColor = Color(0xFF2A8D8D),  // Updated to match the theme color
//                contentColor = Color.White  // Custom app color
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.white),
                    titleContentColor = Color.Black
                )
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.applogo), // Replace with your app's icon or relevant image
                contentDescription = "Plastic Radar Logo",
                modifier = Modifier
                    .size(200.dp) // Increase the size to 200.dp or adjust as needed
                    .clip(CircleShape) // Make the image circular
                    .align(Alignment.CenterHorizontally) // Center horizontally
            )


            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Our Mission",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.green),
                modifier = Modifier.align(CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.cream)) // Custom background color
            ) {
                Text(
                    text = "At Plastic Radar, our mission is to bridge the gap between plastic waste producers and those who can give it a second life. We believe that recycling should be simple, accessible, and impactful for everyone. Whether you're an individual looking to responsibly dispose of plastic waste or a business aiming to minimize your environmental footprint, Plastic Radar connects you with local plastic collectors and recycling companies ready to help.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Text(
                text = "Our Commitment",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.green),
                modifier = Modifier.align(CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.cream))
            ) {
                Text(
                    text = "Our platform empowers communities to take an active role in reducing plastic pollution by turning what was once waste into a valuable resource. We’re committed to building a sustainable future, one piece of plastic at a time.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Text(
                text = "Join Us",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.green),
                modifier = Modifier.align(CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.cream))
            ) {
                Text(
                    text = "Join us on our journey to make recycling as easy as a tap on your phone. Together, we can create a cleaner, greener world.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Add your action here */ },
                modifier = Modifier.align(CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Learn More", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun PreviewAboutUsScreen() {
//    AboutUsScreen(navController = rememberNavController())
//}