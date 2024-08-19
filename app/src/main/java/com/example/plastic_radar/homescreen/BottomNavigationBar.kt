package com.example.plastic_radar.homescreen

import com.example.plastic_radar.R
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.plastic_radar.ui.theme.Teal
import com.example.plastic_radar.data.BottomNavigation
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//import androidx.compose.ui.res.painterResource
//import com.example.plastic_radar.ui.theme.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

val items: List<BottomNavigation> = listOf(
    BottomNavigation(
        title = "Home",
        iconRes = R.drawable.home2, // Replace with your custom drawable
        route = "home"
    ),
    BottomNavigation(
        title = "Dispose",
        iconRes = R.drawable.dispose, // Replace with your custom drawable
        route = "dispose"
    ),
    BottomNavigation(
        title = "Collect",
        iconRes = R.drawable.collector, // Replace with your custom drawable
        route = "collector"
    ),
    BottomNavigation(
        title = "Profile",
        iconRes = R.drawable.profile2, // Replace with your custom drawable
        route = "profile"
    )
)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val context = LocalContext.current as? Activity
    var backPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = navController.currentDestination?.route == "home") {
        if (backPressedOnce) {
            context?.finish()
        } else {
            backPressedOnce = true
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()

            coroutineScope.launch {
                delay(2000)
                backPressedOnce = false
            }
        }
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(Color.White)
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = item.route == navController.currentDestination?.route,
                    onClick = {
                        if (item.route != navController.currentDestination?.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = false
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.title,
                            tint = Color.Black, // Keep original color of the drawable
                            modifier = Modifier.size(24.dp) // Fix icon size here
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = Color.Black
                        )
                    },
                    modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
                )
            }
        }
    }
}
