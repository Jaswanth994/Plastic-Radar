package com.example.plastic_radar

import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthOrMainScreen(auth: FirebaseAuth) {
    var user by remember { mutableStateOf(auth.currentUser) }

    if (user == null) {
        AuthScreen(
            onSignedIn = { signedInUser ->
                user = signedInUser
            }
        )
    } else {
        MainScreen(
            user = user!!,
            onSignOut = {
                auth.signOut()
                user = null
//                navController.navigate("signIn") {
//                    popUpTo("address") { inclusive = true }
            }
        )
    }
}




//@Composable
//fun AuthOrMainScreen(
//    navController: FirebaseAuth,
//    auth: FirebaseAuth
//) {
//    var user by remember { mutableStateOf(auth.currentUser) }
//
//    LaunchedEffect(user) {
//        if (user != null) {
//            // Navigate to the AddressScreen or any other page you specify
//            navController.navigate("address") {
//                popUpTo("authOrMain") { inclusive = true }
//            }
//        }
//    }
//
//    if (user == null) {
//        AuthScreen(
//            onSignedIn = { signedInUser ->
//                user = signedInUser
//            }
//        )
//    } else {
//        MainScreen(
//            user = user!!,
//            onSignOut = {
//                auth.signOut()
//                user = null
//                // Optionally navigate to sign-in screen or handle other logic
//                navController.navigate("signIn") {
//                    popUpTo("address") { inclusive = true }
//                }
//            }
//        )
//    }
//}

