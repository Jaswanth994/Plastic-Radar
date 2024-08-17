package com.example.plastic_radar.Profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
//import com.example.plastic_radar.WalletCard

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Profile Section
        ProfileCard(name = "ROHITH", phoneNumber = "9014977891")

        Spacer(modifier = Modifier.height(16.dp))

        // Wallet Section


        Spacer(modifier = Modifier.height(16.dp))

        // Menu Items
        val items = listOf(
            Pair(Icons.Default.Public, "Change Country"),
            Pair(Icons.Default.Language, "Language"),
            Pair(Icons.Default.Description, "Scrap Order History"),
            Pair(Icons.Default.Receipt, "Bio Waste History"),
            Pair(Icons.Default.EmojiEvents, "Loyalty Points"),
            Pair(Icons.Default.Business, "Merchants"),
            Pair(Icons.Default.Book, "Journals"),
            Pair(Icons.Default.Policy, "Policies"),
            Pair(Icons.Default.Info, "About Us"),
            Pair(Icons.Default.Star, "Rate Us"),
            Pair(Icons.Default.Call, "Call Us"),
            Pair(Icons.AutoMirrored.Filled.Chat, "Enquiry"),
            Pair(Icons.Default.Share, "Share App"),
            Pair(Icons.AutoMirrored.Filled.ExitToApp, "Log Out")
        )

        items.forEach { item ->
            DashboardItem(
                icon = item.first,
                label = item.second,
                onClick = {
                    when (item.second) {
                        "Change Country" -> navController.navigate("change_country")
//                        "Language" -> navController.navigate("change_language")
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewContentList() {
//    ContentList(navController = rememberNavController())
//}
