package com.artemissoftware.daedalusscheduler.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.daedalusscheduler.navigation.destinations.listComposable
import com.artemissoftware.daedalusscheduler.navigation.destinations.splashComposable
import com.artemissoftware.daedalusscheduler.navigation.destinations.taskComposable
import com.artemissoftware.daedalusscheduler.ui.viewmodels.SharedViewModel
import com.artemissoftware.daedalusscheduler.util.Constants.LIST_SCREEN
import com.artemissoftware.daedalusscheduler.util.Constants.SPLASH_SCREEN
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){

    val screen = remember(navController) {
        Screens(navController = navController)
    }


    AnimatedNavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){

        splashComposable (
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable (
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }

}