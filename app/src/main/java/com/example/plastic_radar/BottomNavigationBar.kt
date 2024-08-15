package com.example.plastic_radar


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.tooling.preview.Preview
import com.example.plastic_radar.ui.theme.Teal
import com.example.plastic_radar.data.BottomNavigation
//import androidx.compose.ui.res.painterResource
//import com.example.plastic_radar.ui.theme.*
val items: List<BottomNavigation> =listOf(
    BottomNavigation(
        title = "Home",
        icon = Icons.Default.Home,
        route = "home"
    ),
    BottomNavigation(
        title = "Price List",
        icon = Icons.Default.Wallet,
        route = "price_list"
    ),
    BottomNavigation(
        title = "BWG",
        icon = Icons.Default.Notifications,
        route = "bwg"
    ),
    BottomNavigation(
        title = "Profile",
        icon = Icons.Default.Settings,
        route = "profile"
    )

)
@Preview
@Composable
fun BottomNavigationBar(){
    NavigationBar {
        Row (
            modifier = Modifier.background(Color.White)
        ){
            items.forEach { item->
                NavigationBarItem(
                    selected = item.route == "profile",
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = Teal
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = Color.Black
                        )
                    },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand) // Adding hover effect here
                )
            }
        }
    }
}