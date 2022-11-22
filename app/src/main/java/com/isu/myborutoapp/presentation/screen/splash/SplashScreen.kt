package com.isu.myborutoapp.presentation.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.isu.myborutoapp.R
import com.isu.myborutoapp.navigation.Screen
import com.isu.myborutoapp.ui.theme.Purple500
import com.isu.myborutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()
    val degree = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degree.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if(onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        }
        else{
            navController.navigate(Screen.Welcome.route)
        }
    }
    Splash(degree = degree.value)
}

@Composable
fun Splash(degree: Float) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.ic_logo),
                modifier = Modifier.rotate(degree),
                contentDescription = stringResource(
                    R.string.app_logo))
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Purple700, Purple500))),
            contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.ic_logo),
                modifier = Modifier.rotate(degree),
                contentDescription = stringResource(
                    R.string.app_logo))
        }
    }


}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(degree = 0f)
}