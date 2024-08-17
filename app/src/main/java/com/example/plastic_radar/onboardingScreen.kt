package com.example.plastic_radar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


    //commit
    @OptIn(ExperimentalFoundationApi::class)
    @Preview
    @Composable
    fun OnboardingScreen() {
        // Set the page count (e.g., 3 pages for onboarding)
        val pageCount = 3

        // Initialize the pager state with a starting page and page count
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { pageCount }  // Set the page count here
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val backgroundColor = when (page) {
                0 -> Color(0xFFFFFFFF) // Light blue
                1 -> Color(0xFFFFFFFF) // Beige
                2 -> Color(0xFFFFFFFF)
                else -> Color.White // Default
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = when (page) {
                                0 -> R.drawable.onboarding_image1
                                1 -> R.drawable.onboarding_image2
                                2 -> R.drawable.onboarding_image3
                                else -> R.drawable.onboarding_image1 // Default
                            }
                        ),
                        contentDescription = "Onboarding Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.Crop // Adjust as needed
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = when (page) {
                            0 -> "Welcome to Plastic Radar!"
                            1 -> "Give Away Your Plastic Waste"
                            2 -> "Reduce Plastic Pollution"
                            else -> ""
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = when (page) {
                            0 -> "Your go-to app for connecting plastic waste collectors " +
                                    "with recycling companies."
                            1 -> "Easily give away your plastic waste to those who can recycle it, " +
                                    "contributing to a cleaner environment."
                            2 -> "Join us in our mission to reduce plastic pollution by " +
                                    "using Plastic Radar!"
                            else -> ""
                        },
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Swipe instruction text
                    if (page < pageCount - 1) {
                        Text(
                            text = "Swipe to continue →",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                    }
                }
            }
        }
    }
