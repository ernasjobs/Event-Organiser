package com.sid1818416.eventorganiser.database

import androidx.room.TypeConverter
import com.sid1818416.eventorganiser.database.models.Priority

class Converter {
    @TypeConverter
    fun fromPriority (priority: Priority):String {
        return priority.name
    }
    @TypeConverter
    fun toPriority (priority: String): Priority {
        return Priority.valueOf(priority)
    }
}