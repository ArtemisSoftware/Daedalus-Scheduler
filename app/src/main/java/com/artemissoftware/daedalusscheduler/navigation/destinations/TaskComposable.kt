package com.artemissoftware.daedalusscheduler.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.artemissoftware.daedalusscheduler.util.Action
import com.artemissoftware.daedalusscheduler.util.Constants.TASK_ARGUMENT_KEY
import com.artemissoftware.daedalusscheduler.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){ navBackStackEntry ->

        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

    }
}