package com.iyanuoluwa.todoapp.ui

import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.room.TaskDao

class TaskRepository
constructor(
    private val taskDao: TaskDao,
){
    suspend fun insertTask(task : Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    fun getTasks(): kotlinx.coroutines.flow.Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun getTask(id : Long) : Task = taskDao.getTask(id)

}