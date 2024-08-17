package com.example.plastic_radar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WalletCard(balance: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0FFE0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Aakri Wallet", fontSize = 18.sp, color = Color.Black)
        Spacer(modifier = Modifier.weight(1f))  // Push the balance to the right
        Text(text = balance, fontSize = 18.sp, color = Color(0xFF008000))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWalletCard() {
    WalletCard(balance = "₹0.00")
}
