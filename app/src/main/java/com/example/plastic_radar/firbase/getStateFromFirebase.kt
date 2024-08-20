package com.example.plastic_radar.firbase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// Function to retrieve selected state from Firestore
fun getStateFromFirebase(onStateRetrieved: (String) -> Unit) {
    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val userDocRef = firestore.collection("users").document(userId)
    userDocRef.get()
        .addOnSuccessListener { document ->
            if (document != null && document.contains("selectedState")) {
                val selectedState = document.getString("selectedState")
                selectedState?.let { onStateRetrieved(it) }
            } else {
                onStateRetrieved("Location") // Default or fallback value
            }
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error getting state", e)
            onStateRetrieved("Location") // Handle error by setting a fallback value
        }
}
