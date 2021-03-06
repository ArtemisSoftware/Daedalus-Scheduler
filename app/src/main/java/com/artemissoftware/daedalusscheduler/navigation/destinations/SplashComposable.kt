package com.artemissoftware.daedalusscheduler.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder

import com.google.accompanist.navigation.animation.composable
import com.artemissoftware.daedalusscheduler.ui.screens.splash.SplashScreen
import com.artemissoftware.daedalusscheduler.util.Constants.SPLASH_SCREEN

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = { _, _ ->
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(durationMillis = 300)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}