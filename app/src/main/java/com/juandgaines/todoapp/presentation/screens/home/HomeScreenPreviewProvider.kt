package com.juandgaines.todoapp.presentation.screens.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.juandgaines.todoapp.domain.models.TaskCategory
import com.juandgaines.todoapp.domain.models.TaskModel
import com.juandgaines.todoapp.presentation.uistate.HomeDataState

class HomeScreenPreviewProvider: PreviewParameterProvider<HomeDataState> {
    override val values: Sequence<HomeDataState>
        get() = sequenceOf(
            HomeDataState(
                date = "March 9, 2024",
                summary = "5 incomplete, 5 completed",
                completedTask = completedTask,
                pendingTask = pendingTask
            )
        )
}

val completedTask = mutableListOf<TaskModel>()
    .apply {
        repeat(20){
            add(
                TaskModel(
                    id = it.toString(),
                    title = "Task $it",
                    description = "Description $it",
                    category = TaskCategory.WORK,
                    isCompleted = false
                )
            )
        }
    }

val pendingTask = mutableListOf<TaskModel>()
    .apply {
        repeat(20){
            add(
                TaskModel(
                    id = (it+30).toString(),
                    title = "Task $it",
                    description = "Description $it",
                    category = TaskCategory.OTHER,
                    isCompleted = true
                )
            )
        }
    }