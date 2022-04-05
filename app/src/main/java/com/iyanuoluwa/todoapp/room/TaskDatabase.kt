package com.iyanuoluwa.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.util.Converters

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object {
        const val DATABASE_NAME : String = "todo_app_database"
    }
}