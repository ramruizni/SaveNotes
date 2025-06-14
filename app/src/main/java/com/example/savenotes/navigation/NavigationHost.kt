package com.example.savenotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.savenotes.feature.login.LoginScreen
import com.example.savenotes.feature.main.MainScreen
import com.example.savenotes.navigation.login.LoginRoute
import com.example.savenotes.navigation.main.MainRoute

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                navController = navController
            )
        }

        composable<MainRoute> {
            MainScreen()
        }
    }
}