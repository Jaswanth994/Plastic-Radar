package com.example.plastic_radar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

sealed class Route{
    data class LoginScreen(val name: String="login_screen") : Route()
    data class AuthScreen(val name: String="AuthScreen") : Route()
    data class AuthorMainScreen(val name: String="AuthorMainScreen") : Route()
    data class SplashScreen(val name: String="SplashScreen") : Route()
//    data class LoginScreen(val name: String="login_screen") : Route()
}

@Composable
fun MyNavigation(navHostController: NavHostController) {
    NavHost(
        navHostController = navHostController,
        startDestination = Route.LoginScreen().name,
        ) {
            composable(route = Route.LoginScreen().name) {
                LoginScreen()
            }
    }
}