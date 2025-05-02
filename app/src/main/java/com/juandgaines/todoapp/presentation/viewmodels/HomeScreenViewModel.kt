package com.juandgaines.todoapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juandgaines.todoapp.data.repositoriesimp.TaskLocalRepositoryImp
import com.juandgaines.todoapp.presentation.screens.home.HomeScreenAction
import com.juandgaines.todoapp.presentation.screens.home.HomeScreenEvent
import com.juandgaines.todoapp.presentation.uistate.HomeDataState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    private val taskLocalRepositoryImp = TaskLocalRepositoryImp()

    var state by mutableStateOf(HomeDataState())
        private set

    private val eventChannel = Channel<HomeScreenEvent> ()
    val event = eventChannel.receiveAsFlow()

    init {

        taskLocalRepositoryImp.taskFlow.onEach { taskList ->
            val completedTask = taskList.filter { it.isCompleted }
            val pendingTask = taskList.filter { !it.isCompleted }
            state = state.copy(
                date = "April 19, 2025",
                summary = "${completedTask.size} completed, ${pendingTask.size} incompleted",
                completedTask = completedTask,
                pendingTask = pendingTask
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: HomeScreenAction){
        viewModelScope.launch {
            when(action){
                HomeScreenAction.OnDeleteAllTask -> {
                    taskLocalRepositoryImp.deleteAllTask()
                    eventChannel.send(HomeScreenEvent.DeleteAllTask)
                }
                is HomeScreenAction.OnDeleteTask -> {
                    taskLocalRepositoryImp.removeTask(action.task)
                    eventChannel.send(HomeScreenEvent.DeletedTask)

                }
                is HomeScreenAction.OnToggleTask -> {
                    val updateTask = action.task.copy(isCompleted = !action.task.isCompleted)
                    taskLocalRepositoryImp.updateTask(updateTask)

                }
                else -> Unit
            }
        }
    }
}