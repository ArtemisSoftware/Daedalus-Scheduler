package com.artemissoftware.daedalusscheduler.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.artemissoftware.daedalusscheduler.ui.screens.list.ListScreen
import com.artemissoftware.daedalusscheduler.ui.viewmodels.SharedViewModel
import com.artemissoftware.daedalusscheduler.util.Action
import com.artemissoftware.daedalusscheduler.util.Constants.LIST_ARGUMENT_KEY
import com.artemissoftware.daedalusscheduler.util.Constants.LIST_SCREEN
import com.artemissoftware.daedalusscheduler.util.toAction

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){ navBackStackEntry->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()


        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) } // the stored value will survive activity or process  recreation

        LaunchedEffect(key1 = myAction) {
            if(action != myAction){
                myAction = action
                sharedViewModel.action.value = action
            }
        }


        val databaseAction by sharedViewModel.action


        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}