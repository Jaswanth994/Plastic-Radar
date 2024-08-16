package com.example.plastic_radar

import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
            }
        )
    }
}
