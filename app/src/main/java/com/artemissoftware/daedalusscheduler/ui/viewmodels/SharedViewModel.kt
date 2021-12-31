package com.artemissoftware.daedalusscheduler.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.daedalusscheduler.data.models.Priority
import com.artemissoftware.daedalusscheduler.data.models.ToDoTask
import com.artemissoftware.daedalusscheduler.data.repositories.ToDoRepository
import com.artemissoftware.daedalusscheduler.util.Action
import com.artemissoftware.daedalusscheduler.util.Constants.MAX_TITLE_LENGTH
import com.artemissoftware.daedalusscheduler.util.RequestState
import com.artemissoftware.daedalusscheduler.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
): ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks


    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask


    fun getAllTasks(){

        _allTasks.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }
        catch (ex: Exception){
            _allTasks.value = RequestState.Error(ex)
        }

    }


    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask = toDoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }



    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
                addTask()
            }
            Action.UPDATE -> {
                //updateTask()
            }
            Action.DELETE -> {
                //deleteTask()
            }
            Action.DELETE_ALL -> {
                //deleteAllTasks()
            }
            Action.UNDO -> {
                addTask()
            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }







    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }


    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {

        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}