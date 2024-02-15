package com.example.randomusertest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomusertest.ui.composables.UserDetailScreen
import com.example.randomusertest.ui.composables.UserListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.UserList.route
    ) {
        composable(AppScreens.UserList.route) {
            UserListScreen(navController = navController)
        }
        composable(
            AppScreens.UserDetails.route + "/{fullname}/{email}/{gender}/{registrydate}/{phonenumber}/{latitude}/{longitude}/{profilepicture}",
            arguments = listOf(
                navArgument("fullname") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("registrydate") { type = NavType.StringType },
                navArgument("phonenumber") { type = NavType.StringType },
                navArgument("latitude") { type = NavType.StringType },
                navArgument("longitude") { type = NavType.StringType },
                navArgument("profilepicture") { type = NavType.StringType },
            )
        ) {
            UserDetailScreen(
                navController = navController,
                fullName = it.arguments?.getString("fullname"),
                email = it.arguments?.getString("email"),
                gender = it.arguments?.getString("gender"),
                registryDate = it.arguments?.getString("registrydate"),
                phoneNumber = it.arguments?.getString("phonenumber"),
                latitude = it.arguments?.getString("latitude"),
                longitude = it.arguments?.getString("fulllongitudename"),
                profilePicture = it.arguments?.getString("profilepicture"),
            )
        }
    }
}