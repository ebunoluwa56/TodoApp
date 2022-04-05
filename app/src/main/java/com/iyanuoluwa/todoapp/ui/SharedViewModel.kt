package com.iyanuoluwa.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iyanuoluwa.todoapp.model.Task


class SharedViewModel: ViewModel() {

    private val selectedItem : MutableLiveData<Task> = MutableLiveData()
    var isEdit : Boolean? = null

    fun selectItem(task: Task) {
        selectedItem.value = task
    }

    fun getSelectedItem(): LiveData<Task> {
        return selectedItem
    }

}