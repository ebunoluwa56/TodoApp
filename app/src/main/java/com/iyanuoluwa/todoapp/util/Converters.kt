package com.iyanuoluwa.todoapp.util

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimeStamp(date : Date) : Long {
        return date.time
    }

    @TypeConverter
    fun fromPriority(priority: Priority) : String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority : String) : Priority {
        return Priority.valueOf(priority)
    }
}