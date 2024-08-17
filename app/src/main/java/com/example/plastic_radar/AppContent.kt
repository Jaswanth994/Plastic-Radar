package com.example.plastic_radar

//import androidx.compose.animation.Crossfade
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.lifecycle.Lifecycle
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import kotlinx.coroutines.delay
//
//@Composable
//fun AppContent(navController: NavController, auth: FirebaseAuth) {
//    var showSplashScreen by remember { mutableStateOf(true) }
//
//    LaunchedEffect(Unit) {
//        delay(2000)
//        showSplashScreen = false
//    }
//
//    Crossfade(targetState = showSplashScreen, label = "") { isSplashScreenVisible ->
//        if (isSplashScreenVisible) {
//            SplashScreen(navController) // Pass navController here
//        } else {
//            AuthOrMainScreen(navController, auth )
//             // Modify this to handle navigation as well
//        }
//    }
//}