package com.example.plastic_radar.firbase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

fun saveOrderToFirestore(userId: String, order: Order, firestore: FirebaseFirestore) {
    firestore.collection("users")
        .document(userId)
        .collection("orders")
        .add(order)
        .addOnSuccessListener {
            Log.d("Firestore", "Order successfully saved!")
            // Handle success (e.g., show a toast, navigate to another screen)
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error saving order", e)
            // Handle failure (e.g., show an error message)
        }
}