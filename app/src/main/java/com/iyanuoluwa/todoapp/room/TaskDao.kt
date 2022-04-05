package com.iyanuoluwa.todoapp.room

import androidx.room.*
import com.iyanuoluwa.todoapp.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task : Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks() : Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE task_id == :id")
    suspend fun getTask(id : Long) : Task

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)


}