package com.juandgaines.todoapp.domain.repositories

import com.juandgaines.todoapp.domain.models.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskLocalRepository {

    val taskFlow: Flow<List<TaskModel>>

    suspend fun addTask(task:TaskModel)

    suspend fun updateTask(task: TaskModel)

    suspend fun removeTask(task: TaskModel)

    suspend fun deleteAllTask()

    suspend fun getTaskById(id:String): TaskModel?
}