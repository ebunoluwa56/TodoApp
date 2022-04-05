package com.iyanuoluwa.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iyanuoluwa.todoapp.util.Priority
import java.util.*

@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true) val taskId : Long,

    var task : String,

    var priority : Priority,

    @ColumnInfo(name = "due_date")
    var dueDate : Date,

    @ColumnInfo(name = "created_at")
    var dateCreated : Date,

    @ColumnInfo(name = "is_done")
    val isDone : Boolean
)

