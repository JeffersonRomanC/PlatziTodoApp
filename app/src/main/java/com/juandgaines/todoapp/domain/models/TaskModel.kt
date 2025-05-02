package com.juandgaines.todoapp.domain.models


data class TaskModel(
    val id: String,
    val isCompleted: Boolean,
    val title: String,
    val description: String?,
    val category: TaskCategory?
)