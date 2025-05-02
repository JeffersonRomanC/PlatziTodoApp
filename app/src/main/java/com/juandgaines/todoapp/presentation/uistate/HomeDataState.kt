package com.juandgaines.todoapp.presentation.uistate

import com.juandgaines.todoapp.domain.models.TaskModel

data class HomeDataState(
    val date: String = "",
    val summary: String = "",
    val completedTask: List<TaskModel> = emptyList(),
    val pendingTask: List<TaskModel> = emptyList()
)
