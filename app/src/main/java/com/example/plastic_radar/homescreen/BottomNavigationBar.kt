package com.example.plastic_radar.homescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
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
import androidx.navigation.NavController
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
        title = "Dispose",
        icon = Icons.Default.Wallet,
        route = "dispose"
    ),
    BottomNavigation(
        title = "Collector",
        icon = Icons.Default.Notifications,
        route = "collector"
    ),
    BottomNavigation(
        title = "Profile",
        icon = Icons.Default.Settings,
        route = "profile"
    )

)


@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar {
        Row (
            modifier = Modifier.background(Color.White)
        ){
            items.forEach { item->
                NavigationBarItem(
                    selected = item.route == navController.currentDestination?.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
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