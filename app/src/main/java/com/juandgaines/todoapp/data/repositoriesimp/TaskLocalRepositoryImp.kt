package com.juandgaines.todoapp.data.repositoriesimp

import com.juandgaines.todoapp.domain.models.TaskModel
import com.juandgaines.todoapp.domain.repositories.TaskLocalRepository
import com.juandgaines.todoapp.presentation.screens.home.completedTask
import com.juandgaines.todoapp.presentation.screens.home.pendingTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class TaskLocalRepositoryImp: TaskLocalRepository {

    private val _taskFlow = MutableStateFlow<List<TaskModel>>(emptyList())

    init {
        _taskFlow.value = completedTask + pendingTask
    }

    override val taskFlow: MutableStateFlow<List<TaskModel>>
        get() = _taskFlow

    override suspend fun addTask(task: TaskModel) {
        val tasks = _taskFlow.value.toMutableList()
        tasks.add(task)
        delay(100)
        _taskFlow.value = tasks
    }

    override suspend fun updateTask(task: TaskModel) {
        val tasks = _taskFlow.value.toMutableList()
        val taskIndex = tasks.indexOfFirst { it.id == task.id }
        if(taskIndex != -1) {
            tasks[taskIndex] = task
            delay(100)
            _taskFlow.value = tasks
        }
    }

    override suspend fun removeTask(task: TaskModel) {
        val tasks = _taskFlow.value.toMutableList()
        tasks.remove(task)
        delay(100)
        _taskFlow.value = tasks
    }

    override suspend fun deleteAllTask() {
        _taskFlow.value = emptyList()
    }

    override suspend fun getTaskById(id: String): TaskModel? {
        return _taskFlow.value.firstOrNull {it.id == id}
    }
}