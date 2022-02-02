package com.artemissoftware.daedalusscheduler.navigation

import androidx.navigation.NavHostController
import com.artemissoftware.daedalusscheduler.util.Action
import com.artemissoftware.daedalusscheduler.util.Constants

class Screens(navController: NavHostController) {

    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(Constants.SPLASH_SCREEN) { inclusive = true } //remove from the back stack
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }

}