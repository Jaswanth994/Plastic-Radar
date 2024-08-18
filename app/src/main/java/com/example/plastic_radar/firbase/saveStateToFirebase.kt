package com.example.plastic_radar.firbase

// Import Firestore
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// Function to save selected state in Firestore
fun saveStateToFirebase(state: String) {
    val firestore = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

    // Save state in Firestore under the user's document
    val userDocRef = firestore.collection("users").document(userId)
    userDocRef.update("selectedState", state)
        .addOnSuccessListener {
            Log.d("Firestore", "State successfully updated in Firestore")
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error updating state", e)
        }
}
