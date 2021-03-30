package com.sid1818416.eventorganiser.database

import android.content.Context
import androidx.room.*
import com.sid1818416.eventorganiser.database.models.RegisterEntity
import com.sid1818416.eventorganiser.database.models.ToDoData

@Database(entities = [RegisterEntity::class, ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class RegisterDatabase : RoomDatabase() {

    abstract val registerDatabaseDao: RegisterDatabaseDao
    abstract fun toDoDao(): ToDoDao

    companion object {

        @Volatile
        private var INSTANCE: RegisterDatabase? = null


        fun getInstance(context: Context): RegisterDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}