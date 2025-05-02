package com.juandgaines.todoapp.presentation.screens.home

import com.juandgaines.todoapp.domain.models.TaskModel

sealed interface HomeScreenAction {
    data class OnToggleTask(val task: TaskModel): HomeScreenAction
    data class OnDeleteTask(val task: TaskModel): HomeScreenAction
    data object OnDeleteAllTask: HomeScreenAction
    data object OnAddTask: HomeScreenAction
}