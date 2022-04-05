package com.iyanuoluwa.todoapp.di

import android.content.Context
import com.iyanuoluwa.todoapp.model.Task
import com.iyanuoluwa.todoapp.ui.OnTaskListener
import com.iyanuoluwa.todoapp.ui.TaskAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Singleton
    @Provides
    fun provideAdapter(
        context: Context,
        tasks : List<Task>,
        onTaskListener: OnTaskListener
    ) : TaskAdapter {
        return TaskAdapter(context, tasks, onTaskListener)
    }
}