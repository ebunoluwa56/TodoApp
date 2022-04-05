package com.iyanuoluwa.todoapp.di

import com.iyanuoluwa.todoapp.room.TaskDao
import com.iyanuoluwa.todoapp.ui.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        taskDao: TaskDao
    ) : TaskRepository {
        return TaskRepository(taskDao)
    }
}