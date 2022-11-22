package com.isu.myborutoapp.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.isu.myborutoapp.navigation.Screen
import com.isu.myborutoapp.presentation.common.ListContent
import com.isu.myborutoapp.presentation.componenets.HomeTopBar
import com.isu.myborutoapp.ui.theme.statusBarColor

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }
    Scaffold(topBar = { HomeTopBar{
        navController.navigate(Screen.Search.route)

    } },
        content = {
            ListContent(heroes = allHeroes, navController = navController) }, bottomBar = {
                HomeTopBar{
                    navController.navigate(Screen.Search.route)
                }
        })
}