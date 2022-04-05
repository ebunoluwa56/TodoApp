package com.iyanuoluwa.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.iyanuoluwa.todoapp.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
@Inject
constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun getAllTasks() : LiveData<List<Task>> {
        return taskRepository.getTasks().asLiveData()
    }

    fun insertTask(task : Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    suspend fun getTask(id : Long) {
        viewModelScope.launch {
            taskRepository.getTask(id)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    suspend fun delete(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}