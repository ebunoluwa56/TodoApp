package com.iyanuoluwa.todoapp.ui

import com.iyanuoluwa.todoapp.model.Task

interface OnTaskListener {
    fun onTaskClick(task : Task)
    fun onTaskRadioButtonClick(task: Task)
}