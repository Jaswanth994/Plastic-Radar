package com.example.plastic_radar

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun OnboardingScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val onboardingPages = listOf(
        OnboardingPage(
            title = "Welcome to Plastic Radar!",
            description = "Your go-to app for connecting plastic waste collectors " +
                    "with recycling companies.",
            imageResId = R.drawable.onboarding_image1
        ),
        OnboardingPage(
            title = "Give Away Your Plastic Waste",
            description = "Easily give away your plastic waste to those who can recycle it, " +
                    "contributing to a cleaner environment.",
            imageResId = R.drawable.onboarding_image2
        ),
        OnboardingPage(
            title = "Reduce Plastic Pollution",
            description = "Join us in our mission to reduce plastic pollution by " +
                    "using Plastic Radar!",
            imageResId = R.drawable.onboarding_image3
        )
    )

    var currentPage by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(onboardingPages) { index, page ->
                if (index == currentPage) {
                    OnboardingPageItem(page)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Button(
            onClick = {
                if (currentPage < onboardingPages.size - 1) {
                    currentPage++
                } else {
                    // Mark onboarding as shown and navigate to home screen
                    sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
                    navController.navigate(Routes.AuthOrMainScreen) {
                        popUpTo(Routes.onboardingScreen) { inclusive = true }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = if (currentPage == onboardingPages.size - 1) "Get Started" else "Next")
        }
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageResId: Int
)

@Composable
fun OnboardingPageItem(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Image(
            painter = painterResource(id = page.imageResId),
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = page.description,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}