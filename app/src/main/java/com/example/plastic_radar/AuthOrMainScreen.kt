package com.example.plastic_radar

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.plastic_radar.homescreen.HomeScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun AuthOrMainScreen(navController: NavController,auth: FirebaseAuth) {
    var user by remember { mutableStateOf(auth.currentUser) }

    if (user == null) {
        AuthScreen(
            onSignedIn = { signedInUser ->
                user = signedInUser
            }
        )
    } else {
        navController.navigate(Routes.HomeScreen){
            popUpTo(navController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
//        MainScreen(
//            user = user!!,
//            onSignOut = {
//                auth.signOut()
//                user = null
//            }
//        )
    }
}
