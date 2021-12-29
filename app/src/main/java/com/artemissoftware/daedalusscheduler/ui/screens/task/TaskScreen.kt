package com.artemissoftware.daedalusscheduler.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.artemissoftware.daedalusscheduler.data.models.ToDoTask
import com.artemissoftware.daedalusscheduler.ui.viewmodels.SharedViewModel
import com.artemissoftware.daedalusscheduler.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
//    val title: String by sharedViewModel.title
//    val description: String by sharedViewModel.description
//    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {
//            TaskContent(
//                title = title,
//                onTitleChange = {
//                    sharedViewModel.title.value = it
//                },
//                description = description,
//                onDescriptionChange = {
//                    sharedViewModel.description.value = it
//                },
//                priority = priority,
//                onPrioritySelected = {
//                    sharedViewModel.priority.value = it
//                }
//            )
        }
    )
}