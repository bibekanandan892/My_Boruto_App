package com.isu.myborutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.isu.myborutoapp.presentation.screen.details.DetailsScreen
import com.isu.myborutoapp.presentation.screen.home.HomeScreen
import com.isu.myborutoapp.presentation.screen.search.SearchScreen
import com.isu.myborutoapp.presentation.screen.splash.SplashScreen
import com.isu.myborutoapp.presentation.screen.welcome.WelcomeScreen
import com.isu.myborutoapp.utils.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {

            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Details.route,
            arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })) {
            DetailsScreen(navController = navController)

        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)

        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}