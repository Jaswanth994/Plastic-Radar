package com.example.plastic_radar.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plastic_radar.R
import kotlinx.coroutines.delay

@Preview
@Composable
fun AutoScrollingCardCarousel() {
    val images = listOf(
        R.drawable.image1, // Replace with your drawable resources
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )

    var currentIndex by remember { mutableStateOf(0) }
    val listState = rememberLazyListState()

    // Automatically scroll the carousel
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000) // Delay in milliseconds
            currentIndex = (currentIndex + 1) % images.size
            listState.animateScrollToItem(currentIndex) // Scroll to the next card
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(images) { image ->
            CardItem(image)
        }
    }
}

@Composable
fun CardItem(imageResId: Int) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 4.dp)
            .size(325.dp, 200.dp) // Adjust the card size as needed
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}