 package com.example.plastic_radar.firebase

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

// Data class for Order
data class Order(
    val name: String = "",
    val phoneNumber: String = "",
    val addressName: String = "",
    val city: String = "",
    val selectedOptions: List<String> = emptyList(),
    val quantity: List<String> = emptyList()
)

object OrderRepository {
    @SuppressLint("StaticFieldLeak")
    private val firestore = FirebaseFirestore.getInstance()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    // Function to fetch orders of the logged-in user from Firebase
    fun fetchOrders(onSuccess: (List<Order>) -> Unit, onFailure: (Exception) -> Unit) {
        if (userId != null) {
            firestore.collection("users")
                .document(userId)
                .collection("orders")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val orderList = querySnapshot.documents.mapNotNull { documentSnapshot ->
                        documentSnapshot.toObject<Order>()
                    }
                    onSuccess(orderList)
                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                }
        } else {
            onFailure(Exception("User ID is null"))
        }
    }

    // Function to fetch all orders across all users from Firebase
    fun fetchAllOrders(onSuccess: (List<Order>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collectionGroup("orders")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val orderList = querySnapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject<Order>()
                }
                onSuccess(orderList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}