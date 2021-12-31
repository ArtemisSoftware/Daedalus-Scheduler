package com.artemissoftware.daedalusscheduler.ui.screens.list

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.daedalusscheduler.R
import com.artemissoftware.daedalusscheduler.ui.theme.fabBackgroundColor
import com.artemissoftware.daedalusscheduler.ui.viewmodels.SharedViewModel
import com.artemissoftware.daedalusscheduler.util.SearchAppBarState

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
){

    LaunchedEffect(key1 = true){ //trigger once. Only triggers again when key changes
        Log.d("ListScreen", "LaunchedEffect triggered" )
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action


    val allTasks = sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseActions(action = action)


    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
                  ListContent(
                      tasks = allTasks.value,
                      navigateToTaskScreen = navigateToTaskScreen
                  )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(
                id = R.string.add_button
            ),
            tint = Color.White
        )
    }
}


@Preview
@Composable
private fun ListScreenPreview(){
    //--ListScreen(navigateToTaskScreen = {})
}
