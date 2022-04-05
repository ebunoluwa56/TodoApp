package com.iyanuoluwa.todoapp.di

import android.content.Context
import androidx.room.Room
import com.iyanuoluwa.todoapp.room.TaskDao
import com.iyanuoluwa.todoapp.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideTaskDb(@ApplicationContext context : Context) : TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase) : TaskDao {
        return taskDatabase.taskDao()
    }

}