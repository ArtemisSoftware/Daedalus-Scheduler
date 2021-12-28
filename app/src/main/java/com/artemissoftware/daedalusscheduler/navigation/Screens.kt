package com.artemissoftware.daedalusscheduler.navigation

import androidx.navigation.NavHostController
import com.artemissoftware.daedalusscheduler.util.Action
import com.artemissoftware.daedalusscheduler.util.Constants

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}